package com.cdkj.coin.bo;

import java.math.BigInteger;
import java.util.List;

import com.cdkj.coin.bo.base.IPaginableBO;
import com.cdkj.coin.domain.TokenTransaction;

public interface ITokenTransactionBO extends IPaginableBO<TokenTransaction> {

    public boolean isTokenTransactionExist(String txHash, BigInteger logIndex);

    public String saveTokenTransaction(TokenTransaction data);

    public int refreshStatus(TokenTransaction data, String status);

    public List<TokenTransaction> queryTokenTransactionList(
            TokenTransaction condition);

    public TokenTransaction getTokenTransaction(Long id);

}
