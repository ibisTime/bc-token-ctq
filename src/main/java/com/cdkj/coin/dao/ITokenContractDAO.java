package com.cdkj.coin.dao;

import com.cdkj.coin.dao.base.IBaseDAO;
import com.cdkj.coin.domain.TokenContract;

public interface ITokenContractDAO extends IBaseDAO<TokenContract> {
	String NAMESPACE = ITokenContractDAO.class.getName().concat(".");
}