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
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import com.cdkj.coin.ao.IWanTxAO;
import com.cdkj.coin.bo.ISYSConfigBO;
import com.cdkj.coin.bo.IWTokenContractBO;
import com.cdkj.coin.bo.IWanAddressBO;
import com.cdkj.coin.bo.IWanTokenEventBO;
import com.cdkj.coin.bo.IWanTransactionBO;
import com.cdkj.coin.bo.base.Paginable;
import com.cdkj.coin.common.DateUtil;
import com.cdkj.coin.common.JsonUtil;
import com.cdkj.coin.common.PropertiesUtil;
import com.cdkj.coin.common.SysConstants;
import com.cdkj.coin.domain.SYSConfig;
import com.cdkj.coin.domain.WTokenContract;
import com.cdkj.coin.domain.WanTokenEvent;
import com.cdkj.coin.domain.WanTransaction;
import com.cdkj.coin.dto.req.WanTxPageReq;
import com.cdkj.coin.enums.EPushStatus;
import com.cdkj.coin.enums.ETransactionRecetptStatus;
import com.cdkj.coin.ethereum.OrangeCoinToken.TransferEventResponse;
import com.cdkj.coin.ethereum.Web3JClient;
import com.cdkj.coin.exception.BizException;
import com.cdkj.coin.exception.EBizErrorCode;
import com.cdkj.coin.http.PostSimulater;
import com.cdkj.coin.wanchain.WanClient;

/**
 * @author: haiqingzheng 
 * @since: 2018年5月25日 下午12:56:09 
 * @history:
 */
@Service
public class WanTxAOImpl implements IWanTxAO {

    static final org.slf4j.Logger logger = LoggerFactory
        .getLogger(WanTxAOImpl.class);

    private static Web3j web3j = WanClient.getClient();

    @Autowired
    private IWanAddressBO wanAddressBO;

    @Autowired
    private IWanTransactionBO wanTransactionBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private IWTokenContractBO wtokenContractBO;

    @Autowired
    private IWanTokenEventBO wanTokenEventBO;

    @Override
    public Paginable<WanTransaction> queryTxPage(WanTxPageReq req) {

        //
        WanTransaction condation = new WanTransaction();
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

        return this.wanTransactionBO.getPaginable(
            Integer.valueOf(req.getStart()), Integer.valueOf(req.getLimit()),
            condation);
    }

    /**
     * 只落地充值的地址
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void doWanTransactionSync() {

        boolean isDebug = false;
        //
        try {
            //
            while (true) {

                Long blockNumber = sysConfigBO
                    .getLongValue(SysConstants.CUR_WAN_BLOCK_NUMBER);
                if (isDebug == true) {

                    System.out.println(
                        "*********同步循环开始，扫描区块" + blockNumber + "*******");

                }

                // 获取当前区块
                EthBlock wanBlockResp = web3j
                    .ethGetBlockByNumber(
                        new DefaultBlockParameterNumber(blockNumber), true)
                    .send();
                if (wanBlockResp.getError() != null) {
                    logger.error(
                        "扫描万维区块同步流水发送异常，原因：获取区块-" + wanBlockResp.getError());
                    throw new BizException(EBizErrorCode.DEFAULT.getErrorCode(),
                        "扫描万维区块同步流水发送异常，原因：获取区块-" + wanBlockResp.getError());
                }

                EthBlock.Block currentBlock = wanBlockResp.getResult();

                // 获取当前区块链长度
                BigInteger maxBlockNumber = web3j.ethBlockNumber().send()
                    .getBlockNumber();
                if (isDebug == true) {

                    System.out
                        .println("*********最大区块号" + maxBlockNumber + "*******");
                }

                // 判断是否有足够的区块确认 暂定12
                BigInteger blockConfirm = sysConfigBO
                    .getBigIntegerValue(SysConstants.BLOCK_CONFIRM_WAN);
                if (currentBlock == null || maxBlockNumber
                    .subtract(BigInteger.valueOf(blockNumber))
                    .compareTo(blockConfirm) < 0) {

                    if (isDebug == true) {

                        System.out.println("*********同步循环结束,区块号"
                                + (blockNumber - 1) + "为最后一个可信任区块*******");
                    }
                    break;
                }

                // 如果取到区块信息
                List<WanTransaction> wanTransactionList = new ArrayList<>();
                List<WanTokenEvent> tokenEventList = new ArrayList<>();

                for (EthBlock.TransactionResult txObj : currentBlock
                    .getTransactions()) {

                    EthBlock.TransactionObject tx = (EthBlock.TransactionObject) txObj;
                    String toAddress = tx.getTo();

                    if (StringUtils.isBlank(toAddress)) {
                        continue;
                    }

                    // 检查该交易是否已经处理过，如果已经处理过，直接跳过
                    if (wanTransactionBO.isWanTransactionExist(tx.getHash())) {
                        continue;
                    }

                    // 合约地址是否存在
                    Boolean isTokenContractExist = wtokenContractBO
                        .isWTokenContractExist(toAddress);
                    // WAN地址数量
                    long toWanCount = wanAddressBO.addressCount(toAddress);

                    // 判断是否是我们关注的地址,如果不是跳过
                    if (!isTokenContractExist && toWanCount == 0) {
                        continue;
                    }

                    // 获取交易的receipt
                    TransactionReceipt transactionReceipt = Web3JClient
                        .getClient().ethGetTransactionReceipt(tx.getHash())
                        .send().getResult();
                    // 判断交易是否成功
                    if (!ETransactionRecetptStatus.SUCCESS.getCode()
                        .equals(transactionReceipt.getStatus())) {
                        continue;
                    }

                    // 如果合约存在，向下查询transfer事件
                    if (isTokenContractExist) {
                        boolean isTokenRelate = false;
                        WTokenContract tokenContract = wtokenContractBO
                            .getWTokenContract(toAddress);
                        // 1、获取该交易向下的event
                        List<TransferEventResponse> transferEventList = Web3JClient
                            .loadTransferEvents(transactionReceipt);
                        for (TransferEventResponse transferEventResponse : transferEventList) {
                            long toTokenCount = wanAddressBO
                                .addressCount(transferEventResponse.to);
                            // token地址不存在跳出
                            if (toTokenCount == 0) {
                                continue;
                            }
                            isTokenRelate = true;
                            WanTokenEvent tokenEvent = wanTokenEventBO
                                .convertTokenEvent(transferEventResponse,
                                    tx.getHash(), tokenContract.getSymbol());
                            tokenEventList.add(tokenEvent);

                        }
                        // token转移里面没有我们关心的地址
                        if (!isTokenRelate) {
                            continue;
                        }
                    }

                    // 组成一条transaction
                    WanTransaction wanTransaction = wanTransactionBO.convertTx(
                        tx, transactionReceipt.getGasUsed(),
                        currentBlock.getTimestamp());
                    wanTransactionList.add(wanTransaction);

                }
                // 插入数据库
                this.saveToDB(wanTransactionList, tokenEventList, blockNumber);

            }

        } catch (IOException e1) {

            logger.error("扫描万维区块同步流水发送异常，原因：" + e1.getMessage());
        }

    }

    @Transactional
    public void saveToDB(List<WanTransaction> transactionList,
            List<WanTokenEvent> tokenEventList, Long blockNumber) {
        //
        if (transactionList.isEmpty() == false) {
            this.wanTransactionBO.insertTxList(transactionList);
        }

        if (tokenEventList.isEmpty() == false) {
            wanTokenEventBO.insertEventsList(tokenEventList);
        }

        // 修改_区块遍历信息
        SYSConfig config = sysConfigBO
            .getSYSConfig(SysConstants.CUR_WAN_BLOCK_NUMBER);
        //
        sysConfigBO.refreshSYSConfig(config.getId(),
            String.valueOf(blockNumber + 1), config.getUpdater(),
            config.getRemark());
    }

    // 时间调度任务,定期扫描——未推送的——交易
    public void pushTx() {

        System.out.println("***开始推送扫描****");

        WanTransaction con = new WanTransaction();
        con.setStatus(EPushStatus.UN_PUSH.getCode());
        List<WanTransaction> txList = this.wanTransactionBO.queryWanTxPage(con,
            0, 30);
        // 带出事件event
        for (WanTransaction wanTransaction : txList) {
            List<WanTokenEvent> tokenEventList = wanTokenEventBO
                .queryListByHash(wanTransaction.getHash());
            if (CollectionUtils.isNotEmpty(tokenEventList)) {
                wanTransaction.setTokenEventList(tokenEventList);
            }
        }
        System.out.println("***推送条数：****" + txList.size());
        if (txList.size() > 0) {
            // 推送出去
            try {

                String pushJsonStr = JsonUtil.Object2Json(txList);
                System.out.println("***推送数据：****" + pushJsonStr);
                String url = PropertiesUtil.Config.WAN_PUSH_ADDRESS_URL;
                Properties formProperties = new Properties();
                formProperties.put("wanTxlist", pushJsonStr);
                PostSimulater.requestPostForm(url, formProperties);
            } catch (Exception e) {
                logger.error("回调业务biz异常，原因：" + e.getMessage());
            }
        }
        System.out.println("***结束推送扫描****");

    }

    // 确认推送
    @Override
    public Object confirmPush(List<String> hashList) {

        if (hashList == null || hashList.size() <= 0) {
            throw new BizException(
                EBizErrorCode.PUSH_STATUS_UPDATE_FAILURE.getErrorCode(),
                "请传入正确的json数组" + EBizErrorCode.PUSH_STATUS_UPDATE_FAILURE
                    .getErrorCode());
        }

        this.wanTransactionBO.changeTxStatusToPushed(hashList);
        return new Object();

    }

}
