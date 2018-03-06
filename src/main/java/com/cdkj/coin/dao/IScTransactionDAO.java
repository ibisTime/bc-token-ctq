package com.cdkj.coin.dao;

import com.cdkj.coin.dao.base.IBaseDAO;
import com.cdkj.coin.domain.ScTransaction;

public interface IScTransactionDAO extends IBaseDAO<ScTransaction> {
    String NAMESPACE = IScTransactionDAO.class.getName().concat(".");

    public void updateStatus(ScTransaction data);
}
