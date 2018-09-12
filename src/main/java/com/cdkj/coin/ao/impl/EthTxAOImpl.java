package com.cdkj.coin.ao.impl;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthBlock.TransactionResult;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import com.cdkj.coin.ao.IEthTxAO;
import com.cdkj.coin.bo.IEthAddressBO;
import com.cdkj.coin.bo.IEthTransactionBO;
import com.cdkj.coin.bo.ISYSConfigBO;
import com.cdkj.coin.bo.ITokenAddressBO;
import com.cdkj.coin.bo.ITokenContractBO;
import com.cdkj.coin.bo.ITokenEventBO;
import com.cdkj.coin.bo.base.Paginable;
import com.cdkj.coin.common.DateUtil;
import com.cdkj.coin.common.JsonUtil;
import com.cdkj.coin.common.PropertiesUtil;
import com.cdkj.coin.common.SysConstants;
import com.cdkj.coin.domain.EthTransaction;
import com.cdkj.coin.domain.SYSConfig;
import com.cdkj.coin.domain.TokenContract;
import com.cdkj.coin.domain.TokenEvent;
import com.cdkj.coin.dto.req.EthTxPageReq;
import com.cdkj.coin.enums.EPushStatus;
import com.cdkj.coin.enums.ETransactionRecetptStatus;
import com.cdkj.coin.ethereum.OrangeCoinToken.TransferEventResponse;
import com.cdkj.coin.ethereum.Web3JClient;
import com.cdkj.coin.exception.BizException;
import com.cdkj.coin.exception.EBizErrorCode;
import com.cdkj.coin.http.PostSimulater;

/**
 * Created by tianlei on 2017/十一月/02.
 */
@Service
public class EthTxAOImpl implements IEthTxAO {

    static final org.slf4j.Logger logger = LoggerFactory
        .getLogger(EthTxAOImpl.class);

    private static Web3j web3j = Web3JClient.getClient();

    @Autowired
    private IEthAddressBO ethAddressBO;

    @Autowired
    private IEthTransactionBO ethTransactionBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private ITokenContractBO tokenContractBO;

    @Autowired
    private ITokenAddressBO tokenAddressBO;

    @Autowired
    private ITokenEventBO tokenEventBO;

    @Override
    public Paginable<EthTransaction> queryTxPage(EthTxPageReq req) {

        //
        EthTransaction condation = new EthTransaction();
        condation.setStatus(req.getStatus());
        condation.setTo(req.getTo());
        condation.setFrom(req.getFrom());

        // 时间
        Date startDate = null;
        Date endDate = null;

        if (req.getBlockCreateDatetimeStart() != null) {
            startDate = DateUtil.strToDate(req.getBlockCreateDatetimeStart(),
                DateUtil.DATA_TIME_PATTERN_1);
        }

        if (req.getBlockCreateDatetimeEnd() != null) {
            endDate = DateUtil.strToDate(req.getBlockCreateDatetimeEnd(),
                DateUtil.DATA_TIME_PATTERN_1);
        }

        if (startDate != null && endDate != null) {
            if (startDate.compareTo(endDate) > 0) {
                throw new BizException(EBizErrorCode.DEFAULT.getErrorCode(),
                    "开始时间需 <= 结束时间");
            }
        }

        condation.setBlockCreateDatetimeStart(startDate);
        condation.setBlockCreateDatetimeEnd(endDate);

        return this.ethTransactionBO.getPaginable(
            Integer.valueOf(req.getStart()), Integer.valueOf(req.getLimit()),
            condation);
    }

    /**
     * 只落地充值的地址
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void doEthTransactionSync() {
        boolean isDebug = false;
        try {

            while (true) {

                Long blockNumber = sysConfigBO
                    .getLongValue(SysConstants.CUR_ETH_BLOCK_NUMBER);
                // blockNumber = new Long("2968668");
                if (isDebug == true) {
                    System.out.println("*********同步循环开始，扫描区块" + blockNumber
                            + "*******");
                }

                // 获取当前区块
                EthBlock ethBlockResp = web3j.ethGetBlockByNumber(
                    new DefaultBlockParameterNumber(blockNumber), true).send();
                if (ethBlockResp.getError() != null) {
                    logger.error("扫描以太坊区块同步流水发送异常，原因：获取区块-"
                            + ethBlockResp.getError());
                    throw new BizException("xn00000",
                        "扫描以太坊区块同步流水发送异常，原因：获取区块-" + ethBlockResp.getError());
                }

                EthBlock.Block currentBlock = ethBlockResp.getResult();

                // 获取当前区块链长度
                BigInteger maxBlockNumber = web3j.ethBlockNumber().send()
                    .getBlockNumber();
                if (isDebug == true) {

                    System.out.println("*********最大区块号" + maxBlockNumber
                            + "*******");
                }

                // 判断是否有足够的区块确认 暂定12
                BigInteger blockConfirm = sysConfigBO
                    .getBigIntegerValue(SysConstants.BLOCK_CONFIRM_ETH);
                if (currentBlock == null
                        || maxBlockNumber.subtract(
                            BigInteger.valueOf(blockNumber)).compareTo(
                            blockConfirm) < 0) {

                    if (isDebug == true) {

                        System.out.println("*********同步循环结束,区块号"
                                + (blockNumber - 1) + "为最后一个可信任区块*******");
                    }
                    break;
                }

                // 如果取到区块信息
                List<EthTransaction> ethTransactionList = new ArrayList<>();
                List<TokenEvent> tokenEventList = new ArrayList<>();

                txLoop: for (TransactionResult txObj : currentBlock
                    .getTransactions()) {

                    EthBlock.TransactionObject tx = (EthBlock.TransactionObject) txObj;
                    String toAddress = tx.getTo();
                    // if
                    // (!"0x82808fe6899bcd3ad5e4e0002697e8a662080b4f8861860340156dddcb8e3f98"
                    // .equals(tx.getHash()))
                    // continue;

                    if (StringUtils.isBlank(toAddress)) {
                        continue;
                    }

                    // 检查该交易是否已经处理过，如果已经处理过，直接跳过
                    if (ethTransactionBO.isEthTransactionExist(tx.getHash())) {
                        continue;
                    }

                    // 合约地址是否存在
                    Boolean isTokenContractExist = tokenContractBO
                        .isTokenContractExist(toAddress);

                    // ETH地址数量
                    long toEthCount = ethAddressBO.addressCount(toAddress);

                    // 判断是否是我们关注的地址,如果不是跳过
                    if (!isTokenContractExist && toEthCount == 0) {
                        continue;
                    }

                    // 获取交易的receipt
                    TransactionReceipt transactionReceipt = Web3JClient
                        .getClient().ethGetTransactionReceipt(tx.getHash())
                        .send().getResult();
                    // 判断交易是否成功
                    if (!ETransactionRecetptStatus.SUCCESS.getCode().equals(
                        transactionReceipt.getStatus())) {
                        continue;
                    }

                    // 如果合约存在，向下查询transfer事件
                    if (isTokenContractExist) {
                        TokenContract tokenContract = tokenContractBO
                            .getTokenContract(toAddress);
                        // 1、获取该交易向下的event
                        List<TransferEventResponse> transferEventList = Web3JClient
                            .loadTransferEvents(transactionReceipt);
                        for (TransferEventResponse transferEventResponse : transferEventList) {
                            long toTokenCount = tokenAddressBO.addressCount(
                                transferEventResponse.to,
                                tokenContract.getSymbol());
                            // token地址不存在跳出
                            if (toTokenCount == 0) {
                                continue txLoop;
                            }
                            TokenEvent tokenEvent = tokenEventBO
                                .convertTokenEvent(transferEventResponse,
                                    tx.getHash(), tokenContract.getSymbol());
                            tokenEventList.add(tokenEvent);
                        }
                    }

                    // 组成一条transaction
                    EthTransaction ethTransaction = ethTransactionBO.convertTx(
                        tx, transactionReceipt.getGasUsed(),
                        currentBlock.getTimestamp());
                    ethTransactionList.add(ethTransaction);

                }
                // 插入数据库
                this.saveToDB(ethTransactionList, tokenEventList, blockNumber);
            }
        } catch (IOException e1) {

            logger.error("扫描以太坊区块同步流水发送异常，原因：" + e1.getMessage());
        }
    }

    @Transactional
    public void saveToDB(List<EthTransaction> transactionList,
            List<TokenEvent> tokenEventList, Long blockNumber) {
        //
        if (transactionList.isEmpty() == false) {
            this.ethTransactionBO.insertTxList(transactionList);
        }

        if (tokenEventList.isEmpty() == false) {
            tokenEventBO.insertEventsList(tokenEventList);
        }

        // 修改_区块遍历信息
        SYSConfig config = sysConfigBO
            .getSYSConfig(SysConstants.CUR_ETH_BLOCK_NUMBER);
        //
        sysConfigBO.refreshSYSConfig(config.getId(),
            String.valueOf(blockNumber + 1), config.getUpdater(),
            config.getRemark());
    }

    // 时间调度任务,定期扫描——未推送的——交易
    public void pushTx() {

        EthTransaction con = new EthTransaction();
        con.setStatus(EPushStatus.UN_PUSH.getCode());
        List<EthTransaction> txList = this.ethTransactionBO.queryEthTxPage(con,
            0, 30);
        // 带出事件event
        for (EthTransaction ethTransaction : txList) {
            List<TokenEvent> tokenEventList = tokenEventBO
                .queryListByHash(ethTransaction.getHash());
            if (CollectionUtils.isNotEmpty(tokenEventList)) {
                ethTransaction.setTokenEventList(tokenEventList);
            }
        }

        if (txList.size() > 0) {
            // 推送出去
            try {

                String pushJsonStr = JsonUtil.Object2Json(txList);
                String url = PropertiesUtil.Config.ETH_PUSH_ADDRESS_URL;
                Properties formProperties = new Properties();
                formProperties.put("ethTxlist", pushJsonStr);
                PostSimulater.requestPostForm(url, formProperties);
            } catch (Exception e) {
                logger.error("回调业务biz异常，原因：" + e.getMessage());
            }
        }

    }

    // 确认推送
    @Override
    public Object confirmPush(List<String> hashList) {

        if (hashList == null || hashList.size() <= 0) {
            throw new BizException(
                EBizErrorCode.PUSH_STATUS_UPDATE_FAILURE.getErrorCode(),
                "请传入正确的json数组"
                        + EBizErrorCode.PUSH_STATUS_UPDATE_FAILURE
                            .getErrorCode());
        }

        this.ethTransactionBO.changeTxStatusToPushed(hashList);
        return new Object();

    }

}
