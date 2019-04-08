package com.cdkj.coin.bo.impl;

import com.cdkj.coin.Tron.TrxTx;
import com.cdkj.coin.bo.ITrxTransactionBO;
import com.cdkj.coin.bo.base.PaginableBOImpl;
import com.cdkj.coin.common.Base58;
import com.cdkj.coin.common.ByteArray;
import com.cdkj.coin.common.Sha256Hash;
import com.cdkj.coin.dao.ITrxTransactionDAO;
import com.cdkj.coin.domain.TrxTransaction;
import com.cdkj.coin.enums.EPushStatus;
import com.cdkj.coin.enums.ETrxRet;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class TrxTransactionBOImpl extends PaginableBOImpl<TrxTransaction>
        implements ITrxTransactionBO {

    @Autowired
    private ITrxTransactionDAO trxTransactionDAO;

    @Override
    public TrxTransaction getTransactionByHash(String hash) {
        TrxTransaction trxTransaction = null;
        if (StringUtils.isNotBlank(hash)) {
            TrxTransaction condition = new TrxTransaction();
            condition.setHash(hash);
            trxTransaction = trxTransactionDAO.select(condition);
        }
        return trxTransaction;
    }

    @Override
    public void savaTransaction(TrxTransaction trxTransaction) {
        if (null != trxTransaction) {
            trxTransactionDAO.insert(trxTransaction);
        }
    }

    @Override
    public long getTotalCountByHash(String hash) {
        long count = 0;
        if (StringUtils.isNotBlank(hash)) {
            TrxTransaction condition = new TrxTransaction();
            condition.setHash(hash);
            count = trxTransactionDAO.selectTotalCount(condition);
        }
        return count;
    }

    @Override
    public List<TrxTransaction> convertTx(TrxTx txs) {
        List<TrxTransaction> trxTransactionList = new ArrayList();
        if (CollectionUtils.isNotEmpty(txs.getTransactions())) {
            for (TrxTx.Transaction tx : txs.getTransactions()) {
                if (!tx.getRet().get(0).getContractRet().equals(ETrxRet.SUCCESS.getCode())) {
                    continue;
                }
                TrxTransaction trxTransaction = new TrxTransaction();

                trxTransaction.setHash(tx.getTxID());
                String from  = encode58Check(tx.getRawData().getContract().get(0).getParameter().getValue().getOwnerAddress());
                trxTransaction.setFrom(from);
                String to = encode58Check(tx.getRawData().getContract().get(0).getParameter().getValue().getToAddress());
                trxTransaction.setTo(to);

                long transAmount = tx.getRawData().getContract().get(0).getParameter().getValue().getAmount();
                trxTransaction.setAmount(new BigDecimal(transAmount));
                trxTransaction.setType(tx.getRawData().getContract().get(0).getType());
                trxTransaction.setTypeUrl(tx.getRawData().getContract().get(0).getParameter().getTypeUrl());
                trxTransaction.setStatus(EPushStatus.UN_PUSH.getCode());
                trxTransaction.setCreateDatetime(new Date());
                trxTransactionList.add(trxTransaction);
            }

        }
        return trxTransactionList;
    }

    @Override
    public void addTrxTransactionList(List<TrxTransaction> trxTransactionList) {
        if (CollectionUtils.isNotEmpty(trxTransactionList)) {
            trxTransactionDAO.insertList(trxTransactionList);
        }
    }

    @Override
    public List<TrxTransaction> queryTrxTx(TrxTransaction condition,
            Integer start, Integer limit) {
        return trxTransactionDAO.selectList(condition, start, limit);
    }

    @Override
    public TrxTransaction getTrxTransaction(Long id) {
        TrxTransaction trxTransaction = new TrxTransaction();
        if (null != id) {
            TrxTransaction condition = new TrxTransaction();
            condition.setId(id);
            trxTransaction = trxTransactionDAO.select(condition);
        }
        return trxTransaction;
    }

    @Override
    public void refreshStatus(TrxTransaction data, String status) {
        if (null != data) {
            data.setStatus(status);
            trxTransactionDAO.updateStatus(data);
        }
    }

    public static String encode58Check(String address) {
        byte[] input = ByteArray.fromHexString(address);
        byte[] hash0 = Sha256Hash.hash(input);
        byte[] hash1 = Sha256Hash.hash(hash0);
        byte[] inputCheck = new byte[input.length + 4];
        System.arraycopy(input, 0, inputCheck, 0, input.length);
        System.arraycopy(hash1, 0, inputCheck, input.length, 4);
        return Base58.encode(inputCheck);
    }
}
