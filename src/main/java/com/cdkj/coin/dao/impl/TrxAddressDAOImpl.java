package com.cdkj.coin.dao.impl;

import com.cdkj.coin.dao.ITrxAddressDAO;
import com.cdkj.coin.dao.base.support.AMybatisTemplate;
import com.cdkj.coin.domain.TrxAddress;
import org.springframework.stereotype.Repository;

import java.util.List;

//import com.cdkj.coin.domain.EthAddress;

//import com.cdkj.coin.domain.EthAddress;

@Repository("trxAddressDAOImpl")
public class TrxAddressDAOImpl extends AMybatisTemplate implements
        ITrxAddressDAO {

    @Override
    public int insert(TrxAddress data) {
        return super.insert(NAMESPACE.concat("insert_trxAddress"), data);
    }

    @Override
    public Long selectTotalCount(TrxAddress condition) {
        return super.selectTotalCount(NAMESPACE.concat("selectTotalCount"),
            condition);
    }

    @Override
    public Long selectTotalCountByAddress(String address) {

        TrxAddress condation = new TrxAddress();
        condation.setAddress(address);
        return super.selectTotalCount(
            NAMESPACE.concat("selectTotalCountByAddress"), condation);

    }

    //
    @Override
    public int delete(TrxAddress data) {
        return super.delete(NAMESPACE.concat("delete_trxAddress"), data);
    }

    @Override
    public TrxAddress select(TrxAddress condition) {
        return super.select(NAMESPACE.concat("select_trxAddress"), condition,
            TrxAddress.class);
    }

    @Override
    protected void insertBatch(String statement, List<Object> list) {

        super.insertBatch(NAMESPACE.concat("insertBatch"), list);

    }

    @Override
    public List<TrxAddress> selectList(TrxAddress condition) {
        return super.selectList(NAMESPACE.concat("select_trxAddress"),
            condition, TrxAddress.class);
    }

    @Override
    public List<TrxAddress> selectList(TrxAddress condition, int start,
                                       int count) {
        return super.selectList(NAMESPACE.concat("select_trxAddress"), start,
            count, condition, TrxAddress.class);
    }
}
