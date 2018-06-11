package com.cdkj.coin.ao.impl;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
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
import com.cdkj.coin.bo.IWanAddressBO;
import com.cdkj.coin.bo.IWanTransactionBO;
import com.cdkj.coin.bo.base.Paginable;
import com.cdkj.coin.common.DateUtil;
import com.cdkj.coin.common.JsonUtil;
import com.cdkj.coin.common.PropertiesUtil;
import com.cdkj.coin.common.SysConstants;
import com.cdkj.coin.domain.SYSConfig;
import com.cdkj.coin.domain.WanTransaction;
import com.cdkj.coin.dto.req.WanTxPageReq;
import com.cdkj.coin.enums.EPushStatus;
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

    private static final Logger logger = LoggerFactory
        .getLogger(WanTxAOImpl.class);

    private static Web3j web3j = WanClient.getClient();

    @Autowired
    private IWanAddressBO wanAddressBO;

    @Autowired
    private IWanTransactionBO wanTransactionBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

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
                        "*********万维链同步循环开始，扫描区块" + blockNumber + "*******");

                }

                // 获取当前区块
                EthBlock wanBlockResp = web3j
                    .ethGetBlockByNumber(
                        new DefaultBlockParameterNumber(blockNumber), true)
                    .send();
                if (wanBlockResp.getError() != null) {
                    logger.error(
                        "扫描万维链区块同步流水发送异常，原因：获取区块-" + wanBlockResp.getError());
                }

                //
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

                        System.out.println("*********万维链同步循环结束,区块号"
                                + (blockNumber - 1) + "为最后一个可信任区块*******");
                    }
                    break;
                }

                // 如果取到区块信息
                List<WanTransaction> transactionList = new ArrayList<>();

                for (EthBlock.TransactionResult txObj : currentBlock
                    .getTransactions()) {

                    EthBlock.TransactionObject tx = (EthBlock.TransactionObject) txObj;
                    String toAddress = tx.getTo();
                    String fromAddress = tx.getFrom();

                    if (StringUtils.isBlank(toAddress)
                            || StringUtils.isBlank(fromAddress)) {
                        continue;
                    }

                    // 需要同步，判断是否已经处理过
                    if (wanTransactionBO.isWanTransactionExist(tx.getHash())) {
                        continue;
                    }
                    // 获取交易收据
                    Optional<TransactionReceipt> transactionReceipt = web3j
                        .ethGetTransactionReceipt(tx.getHash()).send()
                        .getTransactionReceipt();

                    if (transactionReceipt.isPresent()) {

                        TransactionReceipt transactionReceipt1 = transactionReceipt
                            .get();
                        BigInteger gasUsed = transactionReceipt1.getGasUsed();
                        WanTransaction wanTransaction = this.wanTransactionBO
                            .convertTx(tx, gasUsed,
                                currentBlock.getTimestamp());
                        transactionList.add(wanTransaction);

                    }

                }
                // 存储
                this.saveToDB(transactionList, blockNumber);

            }

        } catch (IOException e1) {

            logger.error("扫描万维链区块同步流水发送异常，原因：" + e1.getMessage());
        }

    }

    @Transactional
    public void saveToDB(List<WanTransaction> transactionList,
            Long blockNumber) {
        //
        if (transactionList.isEmpty() == false) {

            this.wanTransactionBO.insertTxList(transactionList);

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

        WanTransaction con = new WanTransaction();
        con.setStatus(EPushStatus.UN_PUSH.getCode());
        List<WanTransaction> txList = this.wanTransactionBO.queryWanTxPage(con,
            0, 30);
        if (txList.size() > 0) {
            // 推送出去
            try {

                String pushJsonStr = JsonUtil.Object2Json(txList);
                String url = PropertiesUtil.Config.WAN_PUSH_ADDRESS_URL;
                Properties formProperties = new Properties();
                formProperties.put("wanTxlist", pushJsonStr);
                PostSimulater.requestPostForm(url, formProperties);
            } catch (Exception e) {
                logger.error("万维币回调业务biz异常，原因：" + e.getMessage());
            }
        }

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

    /** 
     * @see com.cdkj.coin.ao.IEthTransactionAO#queryEthTransactionPage(int, int, com.cdkj.coin.domain.EthTransaction)
     */
    @Override
    public Paginable<WanTransaction> queryWanTransactionPage(int start,
            int limit, WanTransaction condition) {
        return wanTransactionBO.getPaginable(start, limit, condition);
    }

}
