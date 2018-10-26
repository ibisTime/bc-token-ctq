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

import com.cdkj.coin.bo.IEthTransactionBO;
import com.cdkj.coin.bo.base.PaginableBOImpl;
import com.cdkj.coin.common.AmountUtil;
import com.cdkj.coin.dao.IEthTransactionDAO;
import com.cdkj.coin.domain.EthTransaction;
import com.cdkj.coin.enums.EPushStatus;
import com.cdkj.coin.exception.BizException;
import com.cdkj.coin.exception.EBizErrorCode;

@Component
public class EthTransactionBOImpl extends PaginableBOImpl<EthTransaction>
        implements IEthTransactionBO {

    @Autowired
    private IEthTransactionDAO ethTransactionDAO;

    @Override
    public EthTransaction convertTx(EthBlock.TransactionObject tx,
            BigInteger gasUsed, BigInteger timestamp) {

        if (tx == null || timestamp == null)
            return null;

        EthTransaction transaction = new EthTransaction();
        transaction.setHash(tx.getHash());
        transaction.setNonce(tx.getNonce());
        transaction.setBlockHash(tx.getBlockHash());
        transaction.setBlockNumber(tx.getBlockNumber());
        transaction.setBlockCreateDatetime(new Date(
            timestamp.longValue() * 1000));

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

        BigDecimal gasFee = AmountUtil.BigInteger2BigDecimal(gasUsed).multiply(
            gasPrice);
        transaction.setGasFee(gasFee);
        transaction.setInput(tx.getInput());
        transaction.setPublicKey(tx.getPublicKey());
        transaction.setRaw(tx.getRaw());
        transaction.setR(tx.getR());

        transaction.setS(tx.getS());
        return transaction;
    }

    @Override
    public int saveEthTransaction(EthTransaction tx) {

        return this.ethTransactionDAO.insert(tx);

    }

    @Override
    public List<EthTransaction> queryEthTransactionList(EthTransaction condition) {
        return ethTransactionDAO.selectList(condition);
    }

    @Override
    public List<EthTransaction> queryEthTxPage(EthTransaction condition,
            int start, int limit) {

        return ethTransactionDAO.selectList(condition, start, limit);
    }

    // 批量改变 交易状态 为已推送
    @Override
    public void changeTxStatusToPushed(List<String> txHashList) {

        List<EthTransaction> txList = new ArrayList<EthTransaction>();

        for (String hash : txHashList) {
            EthTransaction ethTransaction = new EthTransaction();
            ethTransaction.setHash(hash);
            txList.add(ethTransaction);
        }

        this.ethTransactionDAO.updateTxStatus(txList);

    }

    @Override
    public void insertTxList(List<EthTransaction> txList) {
        this.ethTransactionDAO.insertTxList(txList);
    }

    @Override
    public EthTransaction getEthTransaction(String hash) {
        EthTransaction data = null;
        if (StringUtils.isNotBlank(hash)) {
            EthTransaction condition = new EthTransaction();
            condition.setHash(hash);
            data = ethTransactionDAO.select(condition);
            if (data == null) {
                throw new BizException(EBizErrorCode.DEFAULT.getErrorCode(),
                    "以太坊交易记录不存在");
            }
        }

        return data;
    }

    @Override
    public boolean isEthTransactionExist(String hash) {
        boolean result = false;

        EthTransaction condition = new EthTransaction();
        condition.setHash(hash);
        if (ethTransactionDAO.selectTotalCount(condition) > 0) {
            result = true;
        }

        return result;
    }

}
