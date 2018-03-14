package com.cdkj.coin.dao;

import com.cdkj.coin.dao.base.IBaseDAO;
import com.cdkj.coin.domain.TokenTransaction;

public interface ITokenTransactionDAO extends IBaseDAO<TokenTransaction> {
    String NAMESPACE = ITokenTransactionDAO.class.getName().concat(".");

    public int updateStatus(TokenTransaction data);
}
