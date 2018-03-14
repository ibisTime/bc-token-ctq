package com.cdkj.coin.bo.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.coin.bo.ITokenTransactionBO;
import com.cdkj.coin.bo.base.PaginableBOImpl;
import com.cdkj.coin.dao.ITokenTransactionDAO;
import com.cdkj.coin.domain.TokenTransaction;
import com.cdkj.coin.exception.BizException;

@Component
public class TokenTransactionBOImpl extends PaginableBOImpl<TokenTransaction>
        implements ITokenTransactionBO {

    @Autowired
    private ITokenTransactionDAO tokenTransactionDAO;

    @Override
    public boolean isTokenTransactionExist(String txHash, BigInteger logIndex) {
        TokenTransaction condition = new TokenTransaction();
        condition.setHash(txHash);
        condition.setTokenLogIndex(logIndex);
        if (tokenTransactionDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveTokenTransaction(TokenTransaction data) {
        String code = null;
        if (data != null) {
            tokenTransactionDAO.insert(data);
        }
        return code;
    }

    @Override
    public int refreshStatus(TokenTransaction data, String status) {
        int count = 0;
        if (data != null) {
            data.setStatus(status);
            count = tokenTransactionDAO.updateStatus(data);
        }
        return count;
    }

    @Override
    public List<TokenTransaction> queryTokenTransactionList(
            TokenTransaction condition) {
        return tokenTransactionDAO.selectList(condition);
    }

    @Override
    public TokenTransaction getTokenTransaction(Long id) {
        TokenTransaction data = null;
        if (id != null) {
            TokenTransaction condition = new TokenTransaction();
            condition.setId(id);
            data = tokenTransactionDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "token交易记录不存在");
            }
        }
        return data;
    }
}
