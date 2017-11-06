package com.cdkj.coin.dao.impl;

import java.util.List;

import com.cdkj.coin.domain.EthTransaction;
import org.springframework.stereotype.Repository;

import com.cdkj.coin.dao.IEthAddressDAO;
import com.cdkj.coin.dao.base.support.AMybatisTemplate;
import com.cdkj.coin.domain.EthAddress;



@Repository("ethAddressDAOImpl")
public class EthAddressDAOImpl extends AMybatisTemplate implements IEthAddressDAO {

	@Override
	public int insert(EthAddress data) {
		return super.insert(NAMESPACE.concat("insert_ethAddress"), data);
	}


	@Override
	public Long selectTotalCount(EthAddress condition) {
		return super.selectTotalCount(NAMESPACE.concat("selectTotalCount"),condition);
	}


	//
	@Override
	public int delete(EthAddress data) {
		return super.delete(NAMESPACE.concat("delete_ethAddress"), data);
	}


	@Override
	public EthAddress select(EthAddress condition) {
		return super.select(NAMESPACE.concat("select_ethAddress"), condition,EthAddress.class);
	}

	@Override
	protected void insertBatch(String statement, List<Object> list) {

		super.insertBatch(NAMESPACE.concat("insertBatch"), list);

	}

	//	@Override
//	public List<EthTransaction> selectListByStatusList(List<String> statusList, String start, String limit) {
//
//		return super.sel
//
//	}

	@Override
	public List<EthAddress> selectList(EthAddress condition) {
		return super.selectList(NAMESPACE.concat("select_ethAddress"), condition,EthAddress.class);
	}


	@Override
	public List<EthAddress> selectList(EthAddress condition, int start, int count) {
		return super.selectList(NAMESPACE.concat("select_ethAddress"), start, count,condition, EthAddress.class);
	}


}