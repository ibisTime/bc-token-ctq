package com.cdkj.coin.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.coin.dao.ITokenTransactionDAO;
import com.cdkj.coin.dao.base.support.AMybatisTemplate;
import com.cdkj.coin.domain.TokenTransaction;

@Repository("tokenTransactionDAOImpl")
public class TokenTransactionDAOImpl extends AMybatisTemplate
        implements ITokenTransactionDAO {

    @Override
    public int insert(TokenTransaction data) {
        return super.insert(NAMESPACE.concat("insert_tokenTransaction"), data);
    }

    @Override
    public int delete(TokenTransaction data) {
        return super.delete(NAMESPACE.concat("delete_tokenTransaction"), data);
    }

    @Override
    public TokenTransaction select(TokenTransaction condition) {
        return super.select(NAMESPACE.concat("select_tokenTransaction"),
            condition, TokenTransaction.class);
    }

    @Override
    public Long selectTotalCount(TokenTransaction condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_tokenTransaction_count"), condition);
    }

    @Override
    public List<TokenTransaction> selectList(TokenTransaction condition) {
        return super.selectList(NAMESPACE.concat("select_tokenTransaction"),
            condition, TokenTransaction.class);
    }

    @Override
    public List<TokenTransaction> selectList(TokenTransaction condition,
            int start, int count) {
        return super.selectList(NAMESPACE.concat("select_tokenTransaction"),
            start, count, condition, TokenTransaction.class);
    }

    @Override
    public int updateStatus(TokenTransaction data) {
        return super.update(NAMESPACE.concat("update_status"), data);
    }

}
