package com.cdkj.coin.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.coin.dao.IUsdtTransactionDAO;
import com.cdkj.coin.dao.base.support.AMybatisTemplate;
import com.cdkj.coin.domain.UsdtTransaction;

@Repository("usdtTransactionDAOImpl")
public class UsdtTransactionDAOImpl extends AMybatisTemplate implements
        IUsdtTransactionDAO {

    @Override
    public int insert(UsdtTransaction data) {
        return super.insert(NAMESPACE.concat("insert_usdtTransaction"), data);
    }

    @Override
    public int delete(UsdtTransaction data) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public UsdtTransaction select(UsdtTransaction condition) {
        return super.select(NAMESPACE.concat("select_usdtTransaction"),
            condition, UsdtTransaction.class);
    }

    @Override
    public Long selectTotalCount(UsdtTransaction condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_usdtTransaction_count"), condition);
    }

    @Override
    public List<UsdtTransaction> selectList(UsdtTransaction condition) {
        return super.selectList(NAMESPACE.concat("select_usdtTransaction"),
            condition, UsdtTransaction.class);
    }

    @Override
    public List<UsdtTransaction> selectList(UsdtTransaction condition,
            int start, int count) {
        return super.selectList(NAMESPACE.concat("select_usdtTransaction"),
            start, count, condition, UsdtTransaction.class);
    }

    @Override
    public void insertList(List<UsdtTransaction> usdtTransactionList) {
        super.insertBatch(NAMESPACE.concat("insert_usdtTransaction_list"),
            (List) usdtTransactionList);
    }

}
