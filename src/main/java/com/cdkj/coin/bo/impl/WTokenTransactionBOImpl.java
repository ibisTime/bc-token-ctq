package com.cdkj.coin.bo.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.coin.bo.IWTokenTransactionBO;
import com.cdkj.coin.bo.base.PaginableBOImpl;
import com.cdkj.coin.dao.IWTokenTransactionDAO;
import com.cdkj.coin.domain.WTokenTransaction;
import com.cdkj.coin.exception.BizException;

@Component
public class WTokenTransactionBOImpl extends PaginableBOImpl<WTokenTransaction>
        implements IWTokenTransactionBO {

    @Autowired
    private IWTokenTransactionDAO tokenTransactionDAO;

    @Override
    public boolean isWTokenTransactionExist(String txHash,
            BigInteger logIndex) {
        WTokenTransaction condition = new WTokenTransaction();
        condition.setHash(txHash);
        condition.setTokenLogIndex(logIndex);
        if (tokenTransactionDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveWTokenTransaction(WTokenTransaction data) {
        String code = null;
        if (data != null) {
            tokenTransactionDAO.insert(data);
        }
        return code;
    }

    @Override
    public int refreshStatus(WTokenTransaction data, String status) {
        int count = 0;
        if (data != null) {
            data.setStatus(status);
            count = tokenTransactionDAO.updateStatus(data);
        }
        return count;
    }

    @Override
    public List<WTokenTransaction> queryWTokenTransactionList(
            WTokenTransaction condition) {
        return tokenTransactionDAO.selectList(condition);
    }

    @Override
    public WTokenTransaction getWTokenTransaction(Long id) {
        WTokenTransaction data = null;
        if (id != null) {
            WTokenTransaction condition = new WTokenTransaction();
            condition.setId(id);
            data = tokenTransactionDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "token交易记录不存在");
            }
        }
        return data;
    }
}
