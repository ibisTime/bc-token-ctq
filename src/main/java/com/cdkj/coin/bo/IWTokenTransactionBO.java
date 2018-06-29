package com.cdkj.coin.bo;

import java.math.BigInteger;
import java.util.List;

import com.cdkj.coin.bo.base.IPaginableBO;
import com.cdkj.coin.domain.WTokenTransaction;

public interface IWTokenTransactionBO extends IPaginableBO<WTokenTransaction> {

    public boolean isWTokenTransactionExist(String txHash, BigInteger logIndex);

    public String saveWTokenTransaction(WTokenTransaction data);

    public int refreshStatus(WTokenTransaction data, String status);

    public List<WTokenTransaction> queryWTokenTransactionList(
            WTokenTransaction condition);

    public WTokenTransaction getWTokenTransaction(Long id);

}
