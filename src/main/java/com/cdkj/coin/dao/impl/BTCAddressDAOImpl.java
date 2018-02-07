package com.cdkj.coin.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.coin.bitcoin.BTCAddress;
import com.cdkj.coin.dao.IBTCAddressDAO;
import com.cdkj.coin.dao.base.support.AMybatisTemplate;

//import com.cdkj.coin.domain.EthAddress;

//import com.cdkj.coin.domain.EthAddress;

@Repository("btcAddressDAOImpl")
public class BTCAddressDAOImpl extends AMybatisTemplate implements
        IBTCAddressDAO {

    @Override
    public int insert(BTCAddress data) {
        return super.insert(NAMESPACE.concat("insert_btcAddress"), data);
    }

    @Override
    public Long selectTotalCount(BTCAddress condition) {
        return super.selectTotalCount(NAMESPACE.concat("selectTotalCount"),
            condition);
    }

    @Override
    public Long selectTotalCountByAddress(String address) {

        BTCAddress condation = new BTCAddress();
        condation.setAddress(address);
        return super.selectTotalCount(
            NAMESPACE.concat("selectTotalCountByAddress"), condation);

    }

    //
    @Override
    public int delete(BTCAddress data) {
        return super.delete(NAMESPACE.concat("delete_btcAddress"), data);
    }

    @Override
    public BTCAddress select(BTCAddress condition) {
        return super.select(NAMESPACE.concat("select_btcAddress"), condition,
            BTCAddress.class);
    }

    @Override
    protected void insertBatch(String statement, List<Object> list) {

        super.insertBatch(NAMESPACE.concat("insertBatch"), list);

    }

    @Override
    public List<BTCAddress> selectList(BTCAddress condition) {
        return super.selectList(NAMESPACE.concat("select_btcAddress"),
            condition, BTCAddress.class);
    }

    @Override
    public List<BTCAddress> selectList(BTCAddress condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_btcAddress"), start,
            count, condition, BTCAddress.class);
    }
}
