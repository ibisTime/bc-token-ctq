package com.cdkj.coin.dao.impl;

import com.cdkj.coin.dao.ITrxTransactionDAO;
import com.cdkj.coin.dao.ITrxTransactionDAO;
import com.cdkj.coin.dao.base.support.AMybatisTemplate;
import com.cdkj.coin.domain.TrxTransaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("trxTransactionDAOImpl")
public class TrxTransactionDAOImpl extends AMybatisTemplate implements
        ITrxTransactionDAO {

    @Override
    public int insert(TrxTransaction data) {
        return super.insert(NAMESPACE.concat("insert_trxTransaction"), data);
    }

    @Override
    public int delete(TrxTransaction data) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public TrxTransaction select(TrxTransaction condition) {
        return super.select(NAMESPACE.concat("select_trxTransaction"),
            condition, TrxTransaction.class);
    }

    @Override
    public Long selectTotalCount(TrxTransaction condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_trxTransaction_count"), condition);
    }

    @Override
    public List<TrxTransaction> selectList(TrxTransaction condition) {
        return super.selectList(NAMESPACE.concat("select_trxTransaction"),
            condition, TrxTransaction.class);
    }

    @Override
    public List<TrxTransaction> selectList(TrxTransaction condition,
            int start, int count) {
        return super.selectList(NAMESPACE.concat("select_trxTransaction"),
            start, count, condition, TrxTransaction.class);
    }

    @Override
    public void insertList(List<TrxTransaction> trxTransactionList) {
        super.insertBatch(NAMESPACE.concat("insert_trxTransaction_list"),
            (List) trxTransactionList);
    }

    @Override
    public void updateStatus(TrxTransaction data) {
        super.update(NAMESPACE.concat("update_trxTransaction_status"), data);
    }
}
