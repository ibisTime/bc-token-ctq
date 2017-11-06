package com.cdkj.coin.dao;

import com.cdkj.coin.dao.base.IBaseDAO;
import com.cdkj.coin.domain.EthTransaction;
import com.sun.jndi.cosnaming.IiopUrl;

import java.util.List;

public interface IEthTransactionDAO extends IBaseDAO<EthTransaction> {
	String NAMESPACE = IEthTransactionDAO.class.getName().concat(".");

	public void updateTxStatus(List<EthTransaction> txList);

	public void insertTxList(List<EthTransaction> txList);

}