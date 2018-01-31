package com.cdkj.coin.dao;

import java.util.List;

import com.cdkj.coin.dao.base.IBaseDAO;
import com.cdkj.coin.domain.ScTransaction;

public interface IScTransactionDAO extends IBaseDAO<ScTransaction> {
    String NAMESPACE = IScTransactionDAO.class.getName().concat(".");

    public void updateTxStatus(List<ScTransaction> txList);
}
