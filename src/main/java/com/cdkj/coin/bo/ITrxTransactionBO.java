package com.cdkj.coin.bo;

import com.cdkj.coin.Tron.TrxTx;
import com.cdkj.coin.bo.base.IPaginableBO;
import com.cdkj.coin.domain.TrxTransaction;

import java.util.List;

public interface ITrxTransactionBO extends IPaginableBO<TrxTransaction> {

    public TrxTransaction getTransactionByHash(String hash);

    public void savaTransaction(TrxTransaction trxTransaction);

    public long getTotalCountByHash(String hash);

    // 将omni数据转换usdt数据
    public List<TrxTransaction> convertTx(TrxTx txs);

    public void addTrxTransactionList(List<TrxTransaction> transactionList);

    public List<TrxTransaction> queryTrxTx(TrxTransaction condition,
                                             Integer start, Integer limit);

    public TrxTransaction getTrxTransaction(Long id);

    public void refreshStatus(TrxTransaction data, String status);
}
