package com.cdkj.coin.ao;

public interface IUsdtTxAO {
    // 定时器调用，同步账户流水
    public void doUsdtTransactionSync();
}
