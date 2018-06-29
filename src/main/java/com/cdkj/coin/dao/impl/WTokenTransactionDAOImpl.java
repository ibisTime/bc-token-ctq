package com.cdkj.coin.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.coin.dao.IWTokenTransactionDAO;
import com.cdkj.coin.dao.base.support.AMybatisTemplate;
import com.cdkj.coin.domain.WTokenTransaction;

@Repository("wtokenTransactionDAOImpl")
public class WTokenTransactionDAOImpl extends AMybatisTemplate
        implements IWTokenTransactionDAO {

    @Override
    public int insert(WTokenTransaction data) {
        return super.insert(NAMESPACE.concat("insert_wtokenTransaction"), data);
    }

    @Override
    public int delete(WTokenTransaction data) {
        return super.delete(NAMESPACE.concat("delete_wtokenTransaction"), data);
    }

    @Override
    public WTokenTransaction select(WTokenTransaction condition) {
        return super.select(NAMESPACE.concat("select_wtokenTransaction"),
            condition, WTokenTransaction.class);
    }

    @Override
    public Long selectTotalCount(WTokenTransaction condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_wtokenTransaction_count"), condition);
    }

    @Override
    public List<WTokenTransaction> selectList(WTokenTransaction condition) {
        return super.selectList(NAMESPACE.concat("select_wtokenTransaction"),
            condition, WTokenTransaction.class);
    }

    @Override
    public List<WTokenTransaction> selectList(WTokenTransaction condition,
            int start, int count) {
        return super.selectList(NAMESPACE.concat("select_wtokenTransaction"),
            start, count, condition, WTokenTransaction.class);
    }

    @Override
    public int updateStatus(WTokenTransaction data) {
        return super.update(NAMESPACE.concat("update_status"), data);
    }

}
