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

import com.cdkj.coin.ao.IWTokenTxAO;
import com.cdkj.coin.bo.ISYSConfigBO;
import com.cdkj.coin.bo.IWTokenAddressBO;
import com.cdkj.coin.bo.IWTokenContractBO;
import com.cdkj.coin.bo.IWTokenTransactionBO;
import com.cdkj.coin.common.DateUtil;
import com.cdkj.coin.common.JsonUtil;
import com.cdkj.coin.common.PropertiesUtil;
import com.cdkj.coin.common.SysConstants;
import com.cdkj.coin.domain.SYSConfig;
import com.cdkj.coin.domain.WTokenContract;
import com.cdkj.coin.domain.WTokenTransaction;
import com.cdkj.coin.enums.EPushStatus;
import com.cdkj.coin.ethereum.OrangeCoinToken.TransferEventResponse;
import com.cdkj.coin.exception.BizException;
import com.cdkj.coin.exception.EBizErrorCode;
import com.cdkj.coin.http.PostSimulater;
import com.cdkj.coin.wanchain.WanTokenClient;

/**
 * @author: haiqingzheng 
 * @since: 2018年6月29日 下午7:23:40 
 * @history:
 */
@Service
public class WTokenTxAOImpl implements IWTokenTxAO {

    static final Logger logger = LoggerFactory.getLogger(WTokenTxAOImpl.class);

    @Autowired
    private IWTokenContractBO wtokenContractBO;

    @Autowired
    private IWTokenAddressBO wtokenAddressBO;

    @Autowired
    private IWTokenTransactionBO wtokenTransactionBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @SuppressWarnings("rawtypes")
    @Override
    public void doWTokenTransactionSync() {
        boolean isDebug = false;

        try {
            //
            while (true) {

                Long blockNumber = sysConfigBO
                    .getLongValue(SysConstants.CUR_WTOKEN_BLOCK_NUMBER);

                if (isDebug == true) {
                    System.out.println("*********WAN Token同步循环开始，扫描区块"
                            + blockNumber + "*******");
                }

                // 获取当前扫描区块信息
                EthBlock.Block currentBlock = WanTokenClient
                    .getBlock(blockNumber);

                // 获取当前区块链长度
                BigInteger maxBlockNumber = WanTokenClient.getCurBlockNumber();

                if (isDebug == true) {
                    System.out
                        .println("*********最大区块号" + maxBlockNumber + "*******");
                }

                // 判断是否有足够的区块确认 暂定12
                BigInteger blockConfirm = sysConfigBO
                    .getBigIntegerValue(SysConstants.BLOCK_CONFIRM_WTOKEN);
                if (currentBlock == null || maxBlockNumber
                    .subtract(BigInteger.valueOf(blockNumber))
                    .compareTo(blockConfirm) < 0) {

                    if (isDebug == true) {
                        System.out.println("*********同步循环结束,区块号"
                                + (blockNumber - 1) + "为最后一个可信任区块*******");
                    }

                    break;
                }

                List<WTokenTransaction> transactionList = new ArrayList<>();

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
                    if (wtokenContractBO.isWTokenContractExist(toAddress)) {
                        WTokenContract wtokenContract = wtokenContractBO
                            .getWTokenContract(toAddress);
                        TransactionReceipt transactionReceipt = WanTokenClient
                            .getClient().ethGetTransactionReceipt(tx.getHash())
                            .send().getResult();

                        // 1、获取该交易向下的event
                        List<TransferEventResponse> transferEventList = WanTokenClient
                            .loadTransferEvents(transactionReceipt);
                        // 2、遍历eventlist
                        if (CollectionUtils.isNotEmpty(transferEventList)) {
                            for (TransferEventResponse transferEventResponse : transferEventList) {
                                // 检查event是否已经处理过，如果已经处理过，直接跳过
                                if (wtokenTransactionBO
                                    .isWTokenTransactionExist(tx.getHash(),
                                        transferEventResponse.log
                                            .getLogIndex())) {
                                    continue;
                                }
                                // 3、检查event中的 from to 是否是我们关心的地址
                                long fromCount = wtokenAddressBO.addressCount(
                                    transferEventResponse.from,
                                    wtokenContract.getSymbol());
                                long toCount = wtokenAddressBO.addressCount(
                                    transferEventResponse.to,
                                    wtokenContract.getSymbol());
                                if (toCount > 0 || fromCount > 0) {
                                    WTokenTransaction wtokenTransaction = convertWTokenTransaction(
                                        currentBlock, tx, transactionReceipt,
                                        transferEventResponse, wtokenContract);
                                    transactionList.add(wtokenTransaction);
                                }
                            }
                        }
                    }

                }
                // 存储
                this.saveToDB(transactionList, blockNumber);

            }

        } catch (Exception e) {
            logger.error("扫描万维链区块同步流水发送异常，原因：" + e.getMessage());
        }

    }

    private WTokenTransaction convertWTokenTransaction(
            EthBlock.Block currentBlock, EthBlock.TransactionObject tx,
            TransactionReceipt transactionReceipt,
            TransferEventResponse transferEventResponse,
            WTokenContract wtokenContract) {
        WTokenTransaction wtokenTransaction = new WTokenTransaction();
        wtokenTransaction.setHash(tx.getHash());
        wtokenTransaction.setNonce(tx.getNonce());
        wtokenTransaction.setBlockHash(tx.getBlockHash());
        wtokenTransaction.setTransactionIndex(tx.getTransactionIndex());
        wtokenTransaction.setFrom(tx.getFrom());
        wtokenTransaction.setTo(tx.getTo());
        wtokenTransaction.setValue(new BigDecimal(tx.getValue().toString()));
        wtokenTransaction.setInput(tx.getInput());
        wtokenTransaction.setTokenFrom(transferEventResponse.from);
        wtokenTransaction.setTokenTo(transferEventResponse.to);
        wtokenTransaction.setTokenValue(
            new BigDecimal(transferEventResponse.value.toString()));
        wtokenTransaction
            .setTokenLogIndex(transferEventResponse.log.getLogIndex());
        wtokenTransaction.setBlockCreateDatetime(
            DateUtil.TimeStamp2Date(currentBlock.getTimestamp().toString(),
                DateUtil.DATA_TIME_PATTERN_1));
        wtokenTransaction.setSyncDatetime(new Date());
        wtokenTransaction.setBlockNumber(tx.getBlockNumber());
        wtokenTransaction
            .setGasPrice(new BigDecimal(tx.getGasPrice().toString()));
        wtokenTransaction.setGasLimit(tx.getGas());
        wtokenTransaction.setGasUsed(transactionReceipt.getGasUsed());
        wtokenTransaction.setStatus(EPushStatus.UN_PUSH.getCode());
        wtokenTransaction.setSymbol(wtokenContract.getSymbol());
        return wtokenTransaction;
    }

    @Transactional
    public void saveToDB(List<WTokenTransaction> transactionList,
            Long blockNumber) {

        //
        if (transactionList.isEmpty() == false) {

            for (WTokenTransaction wtokenTransaction : transactionList) {
                this.wtokenTransactionBO
                    .saveWTokenTransaction(wtokenTransaction);
            }

        }

        // 修改_区块遍历信息
        SYSConfig config = sysConfigBO
            .getSYSConfig(SysConstants.CUR_WTOKEN_BLOCK_NUMBER);
        //
        sysConfigBO.refreshSYSConfig(config.getId(),
            String.valueOf(blockNumber + 1), config.getUpdater(),
            config.getRemark());

    }

    // 时间调度任务,定期扫描——未推送的——交易
    public void pushTx() {

        WTokenTransaction con = new WTokenTransaction();
        con.setStatus(EPushStatus.UN_PUSH.getCode());
        List<WTokenTransaction> txList = this.wtokenTransactionBO
            .getPaginable(0, 30, con).getList();
        if (txList.size() > 0) {
            // 推送出去
            try {
                String pushJsonStr = JsonUtil.Object2Json(txList);
                String url = PropertiesUtil.Config.WAN_TOKEN_PUSH_ADDRESS_URL;
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
            WTokenTransaction data = wtokenTransactionBO
                .getWTokenTransaction(id);
            wtokenTransactionBO.refreshStatus(data,
                EPushStatus.PUSHED.getCode());
        }

        return new Object();

    }

}
