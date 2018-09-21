package com.cdkj.coin.dao;

import java.util.List;

import com.cdkj.coin.dao.base.IBaseDAO;
import com.cdkj.coin.domain.UsdtTransaction;

public interface IUsdtTransactionDAO extends IBaseDAO<UsdtTransaction> {
    String NAMESPACE = IUsdtTransactionDAO.class.getName().concat(".");

    public void insertList(List<UsdtTransaction> usdtTransactionList);
}
