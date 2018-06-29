package com.cdkj.coin.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.coin.dao.IWTokenContractDAO;
import com.cdkj.coin.dao.base.support.AMybatisTemplate;
import com.cdkj.coin.domain.WTokenContract;

@Repository("wtokenContractDAOImpl")
public class WTokenContractDAOImpl extends AMybatisTemplate
        implements IWTokenContractDAO {

    @Override
    public int insert(WTokenContract data) {
        return super.insert(NAMESPACE.concat("insert_wtokenContract"), data);
    }

    @Override
    public int delete(WTokenContract data) {
        return super.delete(NAMESPACE.concat("delete_wtokenContract"), data);
    }

    @Override
    public WTokenContract select(WTokenContract condition) {
        return super.select(NAMESPACE.concat("select_wtokenContract"),
            condition, WTokenContract.class);
    }

    @Override
    public Long selectTotalCount(WTokenContract condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_wtokenContract_count"), condition);
    }

    @Override
    public List<WTokenContract> selectList(WTokenContract condition) {
        return super.selectList(NAMESPACE.concat("select_wtokenContract"),
            condition, WTokenContract.class);
    }

    @Override
    public List<WTokenContract> selectList(WTokenContract condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_wtokenContract"),
            start, count, condition, WTokenContract.class);
    }

}
