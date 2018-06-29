package com.cdkj.coin.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.coin.dao.IWTokenAddressDAO;
import com.cdkj.coin.dao.base.support.AMybatisTemplate;
import com.cdkj.coin.domain.WTokenAddress;

@Repository("wtokenAddressDAOImpl")
public class WTokenAddressDAOImpl extends AMybatisTemplate
        implements IWTokenAddressDAO {

    @Override
    public int insert(WTokenAddress data) {
        return super.insert(NAMESPACE.concat("insert_wtokenAddress"), data);
    }

    @Override
    public Long selectTotalCount(WTokenAddress condition) {
        return super.selectTotalCount(NAMESPACE.concat("selectTotalCount"),
            condition);
    }

    @Override
    public Long selectTotalCountByAddress(String address, String symbol) {

        WTokenAddress condation = new WTokenAddress();
        condation.setAddress(address);
        condation.setSymbol(symbol);
        return super.selectTotalCount(
            NAMESPACE.concat("selectTotalCountByAddress"), condation);

    }

    @Override
    public int delete(WTokenAddress data) {
        return super.delete(NAMESPACE.concat("delete_wtokenAddress"), data);
    }

    @Override
    public WTokenAddress select(WTokenAddress condition) {
        return super.select(NAMESPACE.concat("select_wtokenAddress"), condition,
            WTokenAddress.class);
    }

    @Override
    protected void insertBatch(String statement, List<Object> list) {

        super.insertBatch(NAMESPACE.concat("insertBatch"), list);

    }

    @Override
    public List<WTokenAddress> selectList(WTokenAddress condition) {
        return super.selectList(NAMESPACE.concat("select_wtokenAddress"),
            condition, WTokenAddress.class);
    }

    @Override
    public List<WTokenAddress> selectList(WTokenAddress condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_wtokenAddress"), start,
            count, condition, WTokenAddress.class);
    }
}
