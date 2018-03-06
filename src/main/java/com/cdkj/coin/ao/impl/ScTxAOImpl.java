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

    @Autowired
    private IScAddressBO scAddressBO;

    @Autowired
    private IScTransactionBO scTransactionBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Override
    public void doScTransactionSync() {

        boolean isDebug = false;

        if (!SiadClient.isUnlock()) {
            System.out.println("*********Siacoin钱包未打开*******");
            return;
        }

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
                    System.out
                        .println("*********最大区块号" + maxBlockNumber + "*******");
                }

                // 判断是否有足够的区块确认 暂定12
                BigInteger blockConfirm = sysConfigBO
                    .getBigIntegerValue(SysConstants.BLOCK_CONFIRM_SC);
                if (maxBlockNumber.subtract(blockNumber)
                    .compareTo(blockConfirm) < 0) {

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
                List<Transaction> transactions = SiadClient
                    .getTransactions(new BigInteger("0"), blockNumber);
                if (CollectionUtils.isNotEmpty(transactions)) {
                    logger.info(
                        "&*&*&*&*共扫描到" + transactions.size() + "个交易&*&*&*&*");
                    for (Transaction tx : transactions) {
                        logger.info("&*&*&*&*开始处理交易:" + tx.getTransactionid()
                                + "&*&*&*&*");
                        // 过滤OSC（内部构造）交易
                        if (isOscTx(tx)) {
                            logger.info("&*&*&*&*交易:" + tx.getTransactionid()
                                    + "为OSC（内部构造）交易，暂不处理&*&*&*&*");
                            continue;
                        }

                        // 矿工费输出信息
                        Output minerOutput = getMinerOutput(tx);
                        if (minerOutput == null) {
                            logger.info("&*&*&*&*交易:" + tx.getTransactionid()
                                    + "找不到矿工费输出信息，暂不处理 &*&*&*&*");
                            continue;
                        }

                        // 若输入地址为钱包相关地址
                        Input input = tx.getInputs().get(0);
                        // 遍历所有Output记录，同步相关的交易记录
                        for (Output output : tx.getOutputs()) {
                            if (scAddressBO
                                .addressCount(output.getRelatedaddress()) > 0
                                    && "siacoin output"
                                        .equals(output.getFundtype())
                                    && !scTransactionBO.isScTransactionExist(
                                        tx.getTransactionid(),
                                        output.getId())) {
                                ScTransaction scTx = new ScTransaction();
                                scTx.setTransactionid(tx.getTransactionid());
                                scTx.setOutputid(output.getId());
                                scTx.setConfirmationheight(
                                    tx.getConfirmationheight());
                                scTx.setConfirmationtimestamp(
                                    tx.getConfirmationtimestamp());
                                scTx.setFrom(input.getRelatedaddress());
                                scTx.setTo(output.getRelatedaddress());
                                scTx.setValue(output.getValue());
                                scTx.setMinerfee(minerOutput.getValue());
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

    private Output getMinerOutput(Transaction tx) {
        Output minerOutput = null;
        int outPutSize = tx.getOutputs().size();
        // 找到挖矿的Output信息
        for (int i = outPutSize - 1; i > 0; i--) {
            Output output = tx.getOutputs().get(i);
            if ("miner fee".equals(output.getFundtype())) {
                minerOutput = output;
                break;
            }
        }
        return minerOutput;
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
            String.valueOf(blockNumber.add(new BigInteger("1"))),
            config.getUpdater(), config.getRemark());

    }

    // 时间调度任务,定期扫描——未推送的——交易
    public void pushTx() {

        ScTransaction con = new ScTransaction();
        con.setStatus(EPushStatus.UN_PUSH.getCode());
        List<ScTransaction> txList = this.scTransactionBO
            .getPaginable(0, 30, con).getList();
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
                "请传入正确的json数组" + BizErrorCode.PUSH_STATUS_UPDATE_FAILURE
                    .getErrorCode());
        }

        this.scTransactionBO.changeTxStatusToPushed(hashList);

    }

}
