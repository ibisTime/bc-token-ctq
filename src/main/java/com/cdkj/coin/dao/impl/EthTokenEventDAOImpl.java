package com.cdkj.coin.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.coin.dao.IEthTokenEventDAO;
import com.cdkj.coin.dao.base.support.AMybatisTemplate;
import com.cdkj.coin.domain.EthTokenEvent;

@Repository("ethTokenEventDAOImpl")
public class EthTokenEventDAOImpl extends AMybatisTemplate
        implements IEthTokenEventDAO {

    @Override
    public int insert(EthTokenEvent data) {
        return super.insert(NAMESPACE.concat("insert_ethTokenEvent"), data);
    }

    @Override
    public int delete(EthTokenEvent data) {
        return super.delete(NAMESPACE.concat("delete_ethTokenEvent"), data);
    }

    @Override
    public EthTokenEvent select(EthTokenEvent condition) {
        return super.select(NAMESPACE.concat("select_ethTokenEvent"), condition,
            EthTokenEvent.class);
    }

    @Override
    public Long selectTotalCount(EthTokenEvent condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_ethTokenEvent_count"), condition);
    }

    @Override
    public List<EthTokenEvent> selectList(EthTokenEvent condition) {
        return super.selectList(NAMESPACE.concat("select_ethTokenEvent"),
            condition, EthTokenEvent.class);
    }

    @Override
    public List<EthTokenEvent> selectList(EthTokenEvent condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_ethTokenEvent"), start,
            count, condition, EthTokenEvent.class);
    }

    @Override
    public void insertEventList(List<EthTokenEvent> ethTokenEventList) {
        super.insertBatch(NAMESPACE.concat("insert_eventList"),
            (List) ethTokenEventList);
    }

}
