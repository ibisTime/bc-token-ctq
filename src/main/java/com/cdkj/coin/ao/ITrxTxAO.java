package com.cdkj.coin.ao;

import java.util.List;

public interface ITrxTxAO {
    // 定时器调用，同步账户流水
    public void doTrxTransactionSync();

    public void confirmPush(List<Long> hashList);
}
