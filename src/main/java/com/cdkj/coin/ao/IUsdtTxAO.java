package com.cdkj.coin.ao;

import java.util.List;

public interface IUsdtTxAO {
    // 定时器调用，同步账户流水
    public void doUsdtTransactionSync();

    public void confirmPush(List<Long> idList);
}
