package com.cdkj.coin.bo.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.protocol.core.methods.response.EthBlock;

import com.cdkj.coin.bo.IWanTransactionBO;
import com.cdkj.coin.bo.base.PaginableBOImpl;
import com.cdkj.coin.common.AmountUtil;
import com.cdkj.coin.dao.IWanTransactionDAO;
import com.cdkj.coin.domain.WanTransaction;
import com.cdkj.coin.enums.EPushStatus;
import com.cdkj.coin.exception.BizException;
import com.cdkj.coin.exception.EBizErrorCode;

@Component
public class WanTransactionBOImpl extends PaginableBOImpl<WanTransaction>
        implements IWanTransactionBO {

    @Autowired
    private IWanTransactionDAO wanTransactionDAO;

    @Override
    public WanTransaction convertTx(EthBlock.TransactionObject tx,
            BigInteger gasUsed, BigInteger timestamp) {

        if (tx == null || timestamp == null)
            return null;

        WanTransaction transaction = new WanTransaction();
        transaction.setHash(tx.getHash());
        transaction.setNonce(tx.getNonce());
        transaction.setBlockHash(tx.getBlockHash());
        transaction.setBlockNumber(tx.getBlockNumber());
        transaction
            .setBlockCreateDatetime(new Date(timestamp.longValue() * 1000));

        transaction.setTransactionIndex(tx.getTransactionIndex());
        transaction.setFrom(tx.getFrom());
        transaction.setTo(tx.getTo());
        transaction.setStatus(EPushStatus.UN_PUSH.getCode());

        BigDecimal ethValue = AmountUtil.BigInteger2BigDecimal(tx.getValue());
        transaction.setValue(ethValue);
        transaction.setSyncDatetime(new Date());

        BigDecimal gasPrice = AmountUtil
            .BigInteger2BigDecimal(tx.getGasPrice());
        transaction.setGasPrice(gasPrice);
        transaction.setGasLimit(tx.getGas());
        transaction.setGasUsed(gasUsed);

        BigDecimal gasFee = AmountUtil.BigInteger2BigDecimal(gasUsed)
            .multiply(gasPrice);
        transaction.setGasFee(gasFee);
        transaction.setInput(tx.getInput());
        transaction.setPublicKey(tx.getPublicKey());
        transaction.setRaw(tx.getRaw());
        transaction.setR(tx.getR());

        transaction.setS(tx.getS());
        return transaction;
    }

    @Override
    public int saveWanTransaction(WanTransaction tx) {

        return this.wanTransactionDAO.insert(tx);

    }

    @Override
    public List<WanTransaction> queryWanTransactionList(
            WanTransaction condition) {
        return wanTransactionDAO.selectList(condition);
    }

    @Override
    public List<WanTransaction> queryWanTxPage(WanTransaction condition,
            int start, int limit) {

        return wanTransactionDAO.selectList(condition, start, limit);
    }

    // 批量改变 交易状态 为已推送
    @Override
    public void changeTxStatusToPushed(List<String> txHashList) {

        List<WanTransaction> txList = new ArrayList<WanTransaction>();

        for (String hash : txHashList) {
            WanTransaction wanTransaction = new WanTransaction();
            wanTransaction.setHash(hash);
            txList.add(wanTransaction);
        }

        this.wanTransactionDAO.updateTxStatus(txList);

    }

    @Override
    public void insertTxList(List<WanTransaction> txList) {
        this.wanTransactionDAO.insertTxList(txList);
    }

    @Override
    public WanTransaction getWanTransaction(String hash) {
        WanTransaction data = null;
        if (StringUtils.isNotBlank(hash)) {
            WanTransaction condition = new WanTransaction();
            condition.setHash(hash);
            data = wanTransactionDAO.select(condition);
            if (data == null) {
                throw new BizException(EBizErrorCode.DEFAULT.getErrorCode(),
                    "万维链交易记录不存在");
            }
        }

        return data;
    }

    @Override
    public boolean isWanTransactionExist(String hash) {
        boolean result = false;

        WanTransaction condition = new WanTransaction();
        condition.setHash(hash);
        if (wanTransactionDAO.selectTotalCount(condition) > 0) {
            result = true;
        }

        return result;
    }

}
