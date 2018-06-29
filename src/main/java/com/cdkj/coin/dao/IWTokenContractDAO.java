package com.cdkj.coin.dao;

import com.cdkj.coin.dao.base.IBaseDAO;
import com.cdkj.coin.domain.WTokenContract;

public interface IWTokenContractDAO extends IBaseDAO<WTokenContract> {
    String NAMESPACE = IWTokenContractDAO.class.getName().concat(".");
}
