package com.cdkj.coin.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.coin.dao.ITokenAddressDAO;
import com.cdkj.coin.dao.base.support.AMybatisTemplate;
import com.cdkj.coin.domain.TokenAddress;

@Repository("tokenAddressDAOImpl")
public class TokenAddressDAOImpl extends AMybatisTemplate implements
        ITokenAddressDAO {

    @Override
    public int insert(TokenAddress data) {
        return super.insert(NAMESPACE.concat("insert_tokenAddress"), data);
    }

    @Override
    public Long selectTotalCount(TokenAddress condition) {
        return super.selectTotalCount(NAMESPACE.concat("selectTotalCount"),
            condition);
    }

    @Override
    public Long selectTotalCountByAddress(String address) {

        TokenAddress condation = new TokenAddress();
        condation.setAddress(address);
        return super.selectTotalCount(
            NAMESPACE.concat("selectTotalCountByAddress"), condation);

    }

    @Override
    public int delete(TokenAddress data) {
        return super.delete(NAMESPACE.concat("delete_tokenAddress"), data);
    }

    @Override
    public TokenAddress select(TokenAddress condition) {
        return super.select(NAMESPACE.concat("select_tokenAddress"), condition,
            TokenAddress.class);
    }

    @Override
    protected void insertBatch(String statement, List<Object> list) {

        super.insertBatch(NAMESPACE.concat("insertBatch"), list);

    }

    @Override
    public List<TokenAddress> selectList(TokenAddress condition) {
        return super.selectList(NAMESPACE.concat("select_tokenAddress"),
            condition, TokenAddress.class);
    }

    @Override
    public List<TokenAddress> selectList(TokenAddress condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_tokenAddress"), start,
            count, condition, TokenAddress.class);
    }
}
