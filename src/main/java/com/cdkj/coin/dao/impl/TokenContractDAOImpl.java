package com.cdkj.coin.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.coin.dao.ITokenContractDAO;
import com.cdkj.coin.dao.base.support.AMybatisTemplate;
import com.cdkj.coin.domain.TokenContract;



@Repository("tokenContractDAOImpl")
public class TokenContractDAOImpl extends AMybatisTemplate implements ITokenContractDAO {


	@Override
	public int insert(TokenContract data) {
		return super.insert(NAMESPACE.concat("insert_tokenContract"), data);
	}


	@Override
	public int delete(TokenContract data) {
		return super.delete(NAMESPACE.concat("delete_tokenContract"), data);
	}


	@Override
	public TokenContract select(TokenContract condition) {
		return super.select(NAMESPACE.concat("select_tokenContract"), condition,TokenContract.class);
	}


	@Override
	public Long selectTotalCount(TokenContract condition) {
		return super.selectTotalCount(NAMESPACE.concat("select_tokenContract_count"),condition);
	}


	@Override
	public List<TokenContract> selectList(TokenContract condition) {
		return super.selectList(NAMESPACE.concat("select_tokenContract"), condition,TokenContract.class);
	}


	@Override
	public List<TokenContract> selectList(TokenContract condition, int start, int count) {
		return super.selectList(NAMESPACE.concat("select_tokenContract"), start, count,condition, TokenContract.class);
	}


}