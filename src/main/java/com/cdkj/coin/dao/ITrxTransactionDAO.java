package com.cdkj.coin.dao;

import com.cdkj.coin.dao.base.IBaseDAO;
import com.cdkj.coin.domain.TrxTransaction;

import java.util.List;

public interface ITrxTransactionDAO extends IBaseDAO<TrxTransaction> {
    String NAMESPACE = ITrxTransactionDAO.class.getName().concat(".");

    public void insertList(List<TrxTransaction> trxTransactionList);

    public void updateStatus(TrxTransaction data);
}
