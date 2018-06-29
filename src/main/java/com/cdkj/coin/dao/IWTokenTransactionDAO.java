package com.cdkj.coin.dao;

import com.cdkj.coin.dao.base.IBaseDAO;
import com.cdkj.coin.domain.WTokenTransaction;

public interface IWTokenTransactionDAO extends IBaseDAO<WTokenTransaction> {
    String NAMESPACE = IWTokenTransactionDAO.class.getName().concat(".");

    public int updateStatus(WTokenTransaction data);
}
