package com.cdkj.coin.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.coin.dao.IEthTransactionDAO;
import com.cdkj.coin.dao.base.support.AMybatisTemplate;
import com.cdkj.coin.domain.EthTransaction;



@Repository("ethTransactionDAOImpl")
public class EthTransactionDAOImpl extends AMybatisTemplate implements IEthTransactionDAO {


	public void updateTxStatus(List<EthTransaction> txList) {

		 super.updateBatch(NAMESPACE.concat("updateTxStatus"),(List) txList);

	}

	@Override
	public int insert(EthTransaction data) {
		return super.insert(NAMESPACE.concat("insertEthTransaction"), data);
	}

	@Override
	public void insertTxList(List<EthTransaction> txList) {
		 super.insertBatch(NAMESPACE.concat("insertTxList"),(List)txList);
	}

	//
	@Override
	public int delete(EthTransaction data) {
		return super.delete(NAMESPACE.concat("delete_ethTransaction"), data);
	}


	@Override
	public EthTransaction select(EthTransaction condition) {
		return super.select(NAMESPACE.concat("select_ethTransaction"), condition,EthTransaction.class);
	}


	@Override
	public Long selectTotalCount(EthTransaction condition) {
		return super.selectTotalCount(NAMESPACE.concat("select_ethTransaction_count"),condition);
	}


	@Override
	public List<EthTransaction> selectList(EthTransaction condition) {
		return super.selectList(NAMESPACE.concat("select_ethTransaction"), condition,EthTransaction.class);
	}


	@Override
	public List<EthTransaction> selectList(EthTransaction condition, int start, int count) {
		return super.selectList(NAMESPACE.concat("select_ethTransaction"), start, count,condition, EthTransaction.class);
	}


}