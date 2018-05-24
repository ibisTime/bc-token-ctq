package com.cdkj.coin.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.coin.dao.IWanAddressDAO;
import com.cdkj.coin.dao.base.support.AMybatisTemplate;
import com.cdkj.coin.domain.WanAddress;

@Repository("wanAddressDAOImpl")
public class WanAddressDAOImpl extends AMybatisTemplate
        implements IWanAddressDAO {

    @Override
    public int insert(WanAddress data) {
        return super.insert(NAMESPACE.concat("insert_wanAddress"), data);
    }

    @Override
    public Long selectTotalCount(WanAddress condition) {
        return super.selectTotalCount(NAMESPACE.concat("selectTotalCount"),
            condition);
    }

    @Override
    public Long selectTotalCountByAddress(String address) {

        WanAddress condation = new WanAddress();
        condation.setAddress(address);
        return super.selectTotalCount(
            NAMESPACE.concat("selectTotalCountByAddress"), condation);

    }

    //
    @Override
    public int delete(WanAddress data) {
        return super.delete(NAMESPACE.concat("delete_wanAddress"), data);
    }

    @Override
    public WanAddress select(WanAddress condition) {
        return super.select(NAMESPACE.concat("select_wanAddress"), condition,
            WanAddress.class);
    }

    @Override
    protected void insertBatch(String statement, List<Object> list) {

        super.insertBatch(NAMESPACE.concat("insertBatch"), list);

    }

    // @Override
    // public List<WanTransaction> selectListByStatusList(List<String>
    // statusList, String start, String limit) {
    //
    // return super.sel
    //
    // }

    @Override
    public List<WanAddress> selectList(WanAddress condition) {
        return super.selectList(NAMESPACE.concat("select_wanAddress"),
            condition, WanAddress.class);
    }

    @Override
    public List<WanAddress> selectList(WanAddress condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_wanAddress"), start,
            count, condition, WanAddress.class);
    }

}
