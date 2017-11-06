package com.cdkj.coin.dao;

import com.cdkj.coin.dao.base.IBaseDAO;
import com.cdkj.coin.domain.EthAddress;
import com.cdkj.coin.domain.EthTransaction;

import java.util.List;

public interface IEthAddressDAO extends IBaseDAO<EthAddress> {

	String NAMESPACE = IEthAddressDAO.class.getName().concat(".");

	@Override
	Long selectTotalCount(EthAddress condition);



}