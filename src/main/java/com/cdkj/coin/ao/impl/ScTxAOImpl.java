package com.cdkj.coin.ao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.protocol.Web3j;

import com.cdkj.coin.ao.IScTxAO;
import com.cdkj.coin.bo.ISYSConfigBO;
import com.cdkj.coin.bo.IScAddressBO;
import com.cdkj.coin.bo.IScTransactionBO;
import com.cdkj.coin.common.JsonUtil;
import com.cdkj.coin.common.PropertiesUtil;
import com.cdkj.coin.common.SysConstants;
import com.cdkj.coin.domain.SYSConfig;
import com.cdkj.coin.domain.ScTransaction;
import com.cdkj.coin.enums.EPushStatus;
import com.cdkj.coin.ethereum.Web3JClient;
import com.cdkj.coin.exception.BizErrorCode;
import com.cdkj.coin.exception.BizException;
import com.cdkj.coin.http.PostSimulater;
import com.cdkj.coin.siacoin.Input;
import com.cdkj.coin.siacoin.Output;
import com.cdkj.coin.siacoin.SiadClient;
import com.cdkj.coin.siacoin.Transaction;

/**
 * @author: haiqingzheng 
 * @since: 2018年1月30日 下午8:24:24 
 * @history:
 */
@Service
public class ScTxAOImpl implements IScTxAO {

    static final org.slf4j.Logger logger = LoggerFactory
        .getLogger(ScAddressAOImpl.class);

    private static Web3j web3j = Web3JClient.getClient();

    @Autowired
    private IScAddressBO scAddressBO;

    @Autowired
    private IScTransactionBO scTransactionBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Override
    public void doScTransactionSync() {

        boolean isDebug = true;
        //
        try {
            //
            while (true) {

                // 当前扫描至那个区块
                BigInteger blockNumber = sysConfigBO
                    .getBigIntegerValue(SysConstants.CUR_SC_BLOCK_NUMBER);
                if (isDebug == true) {
                    System.out.println("*********Siacoin同步循环开始，扫描区块"
                            + blockNumber + "*******");
                }

                // 获取当前区块链长度
                BigInteger maxBlockNumber = SiadClient.getBlockHeight();
                if (isDebug == true) {
                    System.out.println("*********最大区块号" + maxBlockNumber
                            + "*******");
                }

                // 判断是否有足够的区块确认 暂定12
                if (maxBlockNumber.subtract(blockNumber).compareTo(
                    BigInteger.valueOf(1)) < 0) {

                    if (isDebug == true) {
                        System.out.println("*********同步循环结束,区块号"
                                + (blockNumber.subtract(new BigInteger("1")))
                                + "为最后一个可信任区块*******");
                    }
                    break;
                }

                // 如果取到区块信息
                List<ScTransaction> transactionList = new ArrayList<>();

                // 钱包相关交易
                List<Transaction> transactions = SiadClient.getTransactions(
                    blockNumber.subtract(new BigInteger("1")), blockNumber);
                if (CollectionUtils.isNotEmpty(transactions)) {
                    for (Transaction tx : transactions) {
                        // 过滤OSC（内部构造）交易
                        if (isOscTx(tx)) {
                            continue;
                        }
                        // 是否已处理过该交易
                        if (scTransactionBO.getScTransaction(tx
                            .getTransactionid()) != null) {
                            continue;
                        }
                        if (tx.getInputs().size() == 1
                                && tx.getOutputs().size() == 2) {
                            Input fromInfo = tx.getInputs().get(0);
                            Output toInfo = tx.getOutputs().get(0);
                            Output minerInfo = tx.getOutputs().get(1);

                            // 查询改地址是否在我们系统中存在
                            // to 或者 from 为我们的地址就要进行同步
                            long toCount = scAddressBO.addressCount(fromInfo
                                .getRelatedaddress());
                            long fromCount = scAddressBO.addressCount(toInfo
                                .getRelatedaddress());

                            if (toCount > 0 || fromCount > 0) {
                                // 需要同步
                                // 存储
                                ScTransaction scTx = new ScTransaction();
                                scTx.setTransactionid(tx.getTransactionid());
                                scTx.setConfirmationheight(tx
                                    .getConfirmationheight());
                                scTx.setConfirmationtimestamp(tx
                                    .getConfirmationtimestamp());
                                scTx.setFrom(fromInfo.getRelatedaddress());
                                scTx.setTo(toInfo.getRelatedaddress());
                                scTx.setValue(toInfo.getValue());
                                scTx.setMinerfee(minerInfo.getValue());
                                scTx.setSyncDatetime(new Date());
                                scTx.setStatus(EPushStatus.UN_PUSH.getCode());
                                transactionList.add(scTx);
                            }
                        }

                    }
                }
                this.saveToDB(transactionList, blockNumber);
            }

        } catch (Exception e) {

            logger.error("扫描SC区块同步流水发送异常，原因：" + e.getMessage());
        }

    }

    private boolean isOscTx(Transaction transaction) {
        boolean result = true;
        for (Input input : transaction.getInputs()) {
            result = result && input.isWalletaddress();
        }
        for (Output output : transaction.getOutputs()) {
            result = result && output.isWalletaddress();
        }
        return result;
    }

    @Transactional
    public void saveToDB(List<ScTransaction> transactionList,
            BigInteger blockNumber) {
        //
        if (transactionList.isEmpty() == false) {

            for (ScTransaction scTransaction : transactionList) {
                this.scTransactionBO.saveScTransaction(scTransaction);
            }

        }

        // 修改_区块遍历信息
        SYSConfig config = sysConfigBO
            .getSYSConfig(SysConstants.CUR_SC_BLOCK_NUMBER);
        //
        sysConfigBO.refreshSYSConfig(config.getId(),
            String.valueOf(blockNumber.add(new BigInteger("1"))), "code",
            "下次从哪个区块开始扫描");

    }

    // 时间调度任务,定期扫描——未推送的——交易
    public void pushTx() {

        ScTransaction con = new ScTransaction();
        con.setStatus(EPushStatus.UN_PUSH.getCode());
        List<ScTransaction> txList = this.scTransactionBO.getPaginable(0, 30,
            con).getList();
        if (CollectionUtils.isNotEmpty(txList)) {
            // 推送出去
            try {
                String pushJsonStr = JsonUtil.Object2Json(txList);
                String url = PropertiesUtil.Config.SC_PUSH_ADDRESS_URL;
                Properties formProperties = new Properties();
                formProperties.put("scTxlist", pushJsonStr);
                PostSimulater.requestPostForm(url, formProperties);
            } catch (Exception e) {
                logger.error("SC回调业务biz异常，原因：" + e.getMessage());
            }
        }

    }

    // 确认推送
    @Override
    public void confirmPush(List<String> hashList) {

        if (hashList == null || hashList.size() <= 0) {
            throw new BizException(
                BizErrorCode.PUSH_STATUS_UPDATE_FAILURE.getErrorCode(),
                "请传入正确的json数组"
                        + BizErrorCode.PUSH_STATUS_UPDATE_FAILURE
                            .getErrorCode());
        }

        this.scTransactionBO.changeTxStatusToPushed(hashList);

    }

}
