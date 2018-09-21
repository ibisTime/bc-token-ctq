package com.cdkj.coin.bo;

import java.util.List;

import com.cdkj.coin.bo.base.IPaginableBO;
import com.cdkj.coin.domain.UsdtTransaction;
import com.cdkj.coin.omni.OmniTransaction;

public interface IUsdtTransactionBO extends IPaginableBO<UsdtTransaction> {

    public UsdtTransaction getTransactionByHash(String hash);

    public void savaTransaction(UsdtTransaction usdtTransaction);

    public long getTotalCountByAddress(String address);

    // 将omni数据转换usdt数据
    public UsdtTransaction convertTx(OmniTransaction omniTransaction);

    public void addUsdtTransactionList(List<UsdtTransaction> usdtTransactionList);
}
