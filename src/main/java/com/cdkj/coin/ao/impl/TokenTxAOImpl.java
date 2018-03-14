package com.cdkj.coin.ao.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import com.cdkj.coin.ao.ITokenTxAO;
import com.cdkj.coin.bo.ISYSConfigBO;
import com.cdkj.coin.bo.ITokenAddressBO;
import com.cdkj.coin.bo.ITokenContractBO;
import com.cdkj.coin.bo.ITokenTransactionBO;
import com.cdkj.coin.common.DateUtil;
import com.cdkj.coin.common.JsonUtil;
import com.cdkj.coin.common.PropertiesUtil;
import com.cdkj.coin.common.SysConstants;
import com.cdkj.coin.contract.OrangeCoinToken.TransferEventResponse;
import com.cdkj.coin.contract.TokenClient;
import com.cdkj.coin.domain.SYSConfig;
import com.cdkj.coin.domain.TokenTransaction;
import com.cdkj.coin.enums.EPushStatus;
import com.cdkj.coin.ethereum.Web3JClient;
import com.cdkj.coin.exception.BizException;
import com.cdkj.coin.exception.EBizErrorCode;
import com.cdkj.coin.http.PostSimulater;

/**
 * Created by tianlei on 2017/十一月/02.
 */
@Service
public class TokenTxAOImpl implements ITokenTxAO {

    static final Logger logger = LoggerFactory.getLogger(TokenTxAOImpl.class);

    @Autowired
    private ITokenContractBO tokenContractBO;

    @Autowired
    private ITokenAddressBO tokenAddressBO;

    @Autowired
    private ITokenTransactionBO tokenTransactionBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @SuppressWarnings("rawtypes")
    @Override
    public void doTokenTransactionSync() {
        boolean isDebug = true;

        try {
            //
            while (true) {

                Long blockNumber = sysConfigBO
                    .getLongValue(SysConstants.CUR_TOKEN_BLOCK_NUMBER);

                if (isDebug == true) {
                    System.out.println(
                        "*********Token同步循环开始，扫描区块" + blockNumber + "*******");
                }

                // 获取当前扫描区块信息
                EthBlock.Block currentBlock = Web3JClient.getBlock(blockNumber);

                // 获取当前区块链长度
                BigInteger maxBlockNumber = Web3JClient.getCurBlockNumber();

                if (isDebug == true) {
                    System.out
                        .println("*********最大区块号" + maxBlockNumber + "*******");
                }

                // 判断是否有足够的区块确认 暂定12
                BigInteger blockConfirm = sysConfigBO
                    .getBigIntegerValue(SysConstants.BLOCK_CONFIRM_TOKEN);
                if (currentBlock == null || maxBlockNumber
                    .subtract(BigInteger.valueOf(blockNumber))
                    .compareTo(blockConfirm) < 0) {

                    if (isDebug == true) {
                        System.out.println("*********同步循环结束,区块号"
                                + (blockNumber - 1) + "为最后一个可信任区块*******");
                    }

                    break;
                }

                List<TokenTransaction> transactionList = new ArrayList<>();

                // 如果取到区块信息
                for (EthBlock.TransactionResult txObj : currentBlock
                    .getTransactions()) {

                    EthBlock.TransactionObject tx = (EthBlock.TransactionObject) txObj;
                    String toAddress = tx.getTo();
                    String fromAddress = tx.getFrom();

                    if (StringUtils.isBlank(toAddress)
                            || StringUtils.isBlank(fromAddress)) {
                        continue;
                    }

                    // 如果to地址是橙提取关心合约地址，说明是执行合约的交易，进行解析
                    if (tokenContractBO.isTokenContractExist(toAddress)) {

                        TransactionReceipt transactionReceipt = Web3JClient
                            .getClient().ethGetTransactionReceipt(tx.getHash())
                            .send().getResult();

                        // 1、获取该交易向下的event
                        List<TransferEventResponse> transferEventList = TokenClient
                            .loadTransferEvents(transactionReceipt);
                        // 2、遍历eventlist
                        if (CollectionUtils.isNotEmpty(transferEventList)) {
                            for (TransferEventResponse transferEventResponse : transferEventList) {
                                // 检查event是否已经处理过，如果已经处理过，直接跳过
                                if (tokenTransactionBO.isTokenTransactionExist(
                                    tx.getHash(),
                                    transferEventResponse.log.getLogIndex())) {
                                    continue;
                                }
                                // 3、检查event中的 from to 是否是我们关心的地址
                                long fromCount = tokenAddressBO
                                    .addressCount(fromAddress);
                                long toCount = tokenAddressBO
                                    .addressCount(toAddress);
                                if (toCount > 0 || fromCount > 0) {
                                    TokenTransaction tokenTransaction = convertTokenTransaction(
                                        currentBlock, tx, transactionReceipt,
                                        transferEventResponse);
                                    transactionList.add(tokenTransaction);
                                }
                            }
                        }
                    }

                }
                // 存储
                this.saveToDB(transactionList, blockNumber);

            }

        } catch (Exception e) {
            logger.error("扫描以太坊区块同步流水发送异常，原因：" + e.getMessage());
        }

    }

    private TokenTransaction convertTokenTransaction(
            EthBlock.Block currentBlock, EthBlock.TransactionObject tx,
            TransactionReceipt transactionReceipt,
            TransferEventResponse transferEventResponse) {
        TokenTransaction tokenTransaction = new TokenTransaction();
        tokenTransaction.setHash(tx.getHash());
        tokenTransaction.setNonce(tx.getNonce());
        tokenTransaction.setBlockHash(tx.getBlockHash());
        tokenTransaction.setTransactionIndex(tx.getTransactionIndex());
        tokenTransaction.setFrom(tx.getFrom());
        tokenTransaction.setTo(tx.getTo());
        tokenTransaction.setValue(new BigDecimal(tx.getValue().toString()));
        tokenTransaction.setInput(tx.getInput());
        tokenTransaction.setTokenFrom(transferEventResponse.from);
        tokenTransaction.setTokenTo(transferEventResponse.to);
        tokenTransaction.setTokenValue(
            new BigDecimal(transferEventResponse.value.toString()));
        tokenTransaction
            .setTokenLogIndex(transferEventResponse.log.getLogIndex());
        tokenTransaction.setBlockCreateDatetime(
            DateUtil.TimeStamp2Date(currentBlock.getTimestamp().toString(),
                DateUtil.DATA_TIME_PATTERN_1));
        tokenTransaction.setSyncDatetime(new Date());
        tokenTransaction.setBlockNumber(tx.getBlockNumber());
        tokenTransaction
            .setGasPrice(new BigDecimal(tx.getGasPrice().toString()));
        tokenTransaction.setGasLimit(tx.getGas());
        tokenTransaction.setGasUsed(transactionReceipt.getGasUsed());
        tokenTransaction.setStatus(EPushStatus.UN_PUSH.getCode());
        return tokenTransaction;
    }

    @Transactional
    public void saveToDB(List<TokenTransaction> transactionList,
            Long blockNumber) {

        //
        if (transactionList.isEmpty() == false) {

            for (TokenTransaction tokenTransaction : transactionList) {
                this.tokenTransactionBO.saveTokenTransaction(tokenTransaction);
            }

        }

        // 修改_区块遍历信息
        SYSConfig config = sysConfigBO
            .getSYSConfig(SysConstants.CUR_TOKEN_BLOCK_NUMBER);
        //
        sysConfigBO.refreshSYSConfig(config.getId(),
            String.valueOf(blockNumber + 1), config.getUpdater(),
            config.getRemark());

    }

    // 时间调度任务,定期扫描——未推送的——交易
    public void pushTx() {

        TokenTransaction con = new TokenTransaction();
        con.setStatus(EPushStatus.UN_PUSH.getCode());
        List<TokenTransaction> txList = this.tokenTransactionBO
            .getPaginable(0, 30, con).getList();
        if (txList.size() > 0) {
            // 推送出去
            try {
                String pushJsonStr = JsonUtil.Object2Json(txList);
                String url = PropertiesUtil.Config.TOKEN_PUSH_ADDRESS_URL;
                Properties formProperties = new Properties();
                formProperties.put("tokenTxlist", pushJsonStr);
                PostSimulater.requestPostForm(url, formProperties);
            } catch (Exception e) {
                logger.error("回调业务biz异常，原因：" + e.getMessage());
            }
        }

    }

    // 确认推送
    @Override
    public Object confirmPush(List<Long> idList) {

        if (idList == null || idList.size() <= 0) {
            throw new BizException(
                EBizErrorCode.PUSH_STATUS_UPDATE_FAILURE.getErrorCode(),
                "请传入正确的json数组" + EBizErrorCode.PUSH_STATUS_UPDATE_FAILURE
                    .getErrorCode());
        }

        for (Long id : idList) {
            TokenTransaction data = tokenTransactionBO.getTokenTransaction(id);
            tokenTransactionBO.refreshStatus(data,
                EPushStatus.PUSHED.getCode());
        }

        return new Object();

    }

}
