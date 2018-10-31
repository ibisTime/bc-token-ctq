package com.cdkj.coin.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.coin.dao.IWanTokenEventDAO;
import com.cdkj.coin.dao.base.support.AMybatisTemplate;
import com.cdkj.coin.domain.WanTokenEvent;

@Repository("wanTokenEventDAOImpl")
public class WanTokenEventDAOImpl extends AMybatisTemplate
        implements IWanTokenEventDAO {

    @Override
    public int insert(WanTokenEvent data) {
        return super.insert(NAMESPACE.concat("insert_wanTokenEvent"), data);
    }

    @Override
    public int delete(WanTokenEvent data) {
        return super.delete(NAMESPACE.concat("delete_wanTokenEvent"), data);
    }

    @Override
    public WanTokenEvent select(WanTokenEvent condition) {
        return super.select(NAMESPACE.concat("select_wanTokenEvent"), condition,
            WanTokenEvent.class);
    }

    @Override
    public Long selectTotalCount(WanTokenEvent condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_wanTokenEvent_count"), condition);
    }

    @Override
    public List<WanTokenEvent> selectList(WanTokenEvent condition) {
        return super.selectList(NAMESPACE.concat("select_wanTokenEvent"),
            condition, WanTokenEvent.class);
    }

    @Override
    public List<WanTokenEvent> selectList(WanTokenEvent condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_wanTokenEvent"), start,
            count, condition, WanTokenEvent.class);
    }

    @Override
    public void insertEventList(List<WanTokenEvent> wanTokenEventList) {
        super.insertBatch(NAMESPACE.concat("insert_eventList"),
            (List) wanTokenEventList);
    }

}
