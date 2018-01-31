package com.cdkj.coin.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.coin.dao.IScAddressDAO;
import com.cdkj.coin.dao.base.support.AMybatisTemplate;
import com.cdkj.coin.domain.ScAddress;

@Repository("scAddressDAOImpl")
public class ScAddressDAOImpl extends AMybatisTemplate implements IScAddressDAO {

    @Override
    public int insert(ScAddress data) {
        return super.insert(NAMESPACE.concat("insert_scAddress"), data);
    }

    @Override
    public Long selectTotalCount(ScAddress condition) {
        return super.selectTotalCount(NAMESPACE.concat("selectTotalCount"),
            condition);
    }

    @Override
    public Long selectTotalCountByAddress(String address) {

        ScAddress condation = new ScAddress();
        condation.setAddress(address);
        return super.selectTotalCount(
            NAMESPACE.concat("selectTotalCountByAddress"), condation);

    }

    @Override
    public int delete(ScAddress data) {
        return 0;
    }

    @Override
    public ScAddress select(ScAddress condition) {
        return super.select(NAMESPACE.concat("select_scAddress"), condition,
            ScAddress.class);
    }

    @Override
    protected void insertBatch(String statement, List<Object> list) {

        super.insertBatch(NAMESPACE.concat("insertBatch"), list);

    }

    @Override
    public List<ScAddress> selectList(ScAddress condition) {
        return super.selectList(NAMESPACE.concat("select_scAddress"),
            condition, ScAddress.class);
    }

    @Override
    public List<ScAddress> selectList(ScAddress condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_scAddress"), start,
            count, condition, ScAddress.class);
    }

}
