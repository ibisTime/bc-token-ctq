package com.cdkj.coin.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.coin.dao.IScTransactionDAO;
import com.cdkj.coin.dao.base.support.AMybatisTemplate;
import com.cdkj.coin.domain.ScTransaction;

@Repository("scTransactionDAOImpl")
public class ScTransactionDAOImpl extends AMybatisTemplate
        implements IScTransactionDAO {

    @Override
    public int insert(ScTransaction data) {
        return super.insert(NAMESPACE.concat("insert_scTransaction"), data);
    }

    @Override
    public int delete(ScTransaction data) {
        return super.delete(NAMESPACE.concat("delete_scTransaction"), data);
    }

    @Override
    public ScTransaction select(ScTransaction condition) {
        return super.select(NAMESPACE.concat("select_scTransaction"), condition,
            ScTransaction.class);
    }

    @Override
    public Long selectTotalCount(ScTransaction condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_scTransaction_count"), condition);
    }

    @Override
    public List<ScTransaction> selectList(ScTransaction condition) {
        return super.selectList(NAMESPACE.concat("select_scTransaction"),
            condition, ScTransaction.class);
    }

    @Override
    public List<ScTransaction> selectList(ScTransaction condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_scTransaction"), start,
            count, condition, ScTransaction.class);
    }

    public void updateStatus(ScTransaction data) {

        super.update(NAMESPACE.concat("update_status"), data);

    }

}
