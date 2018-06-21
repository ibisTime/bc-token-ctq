package com.cdkj.coin.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.coin.dao.IWanTransactionDAO;
import com.cdkj.coin.dao.base.support.AMybatisTemplate;
import com.cdkj.coin.domain.WanTransaction;

@Repository("wanTransactionDAOImpl")
public class WanTransactionDAOImpl extends AMybatisTemplate
        implements IWanTransactionDAO {

    public void updateTxStatus(List<WanTransaction> txList) {

        super.updateBatch(NAMESPACE.concat("updateTxStatus"), (List) txList);

    }

    @Override
    public int insert(WanTransaction data) {
        return super.insert(NAMESPACE.concat("insertWanTransaction"), data);
    }

    @Override
    public void insertTxList(List<WanTransaction> txList) {
        super.insertBatch(NAMESPACE.concat("insertTxList"), (List) txList);
    }

    //
    @Override
    public int delete(WanTransaction data) {
        return super.delete(NAMESPACE.concat("delete_wanTransaction"), data);
    }

    @Override
    public WanTransaction select(WanTransaction condition) {
        return super.select(NAMESPACE.concat("select_wanTransaction"),
            condition, WanTransaction.class);
    }

    @Override
    public Long selectTotalCount(WanTransaction condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_wanTransaction_count"), condition);
    }

    @Override
    public List<WanTransaction> selectList(WanTransaction condition) {
        return super.selectList(NAMESPACE.concat("select_wanTransaction"),
            condition, WanTransaction.class);
    }

    @Override
    public List<WanTransaction> selectList(WanTransaction condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_wanTransaction"),
            start, count, condition, WanTransaction.class);
    }

    @Override
    public void updateTxGasUsed(WanTransaction data) {
        super.update(NAMESPACE.concat("update_gasUsed"), data);
    }

}
