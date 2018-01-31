package com.cdkj.coin.ao;

import java.util.List;

/**
 * Created by tianlei on 2017/十一月/02.
 */
public interface IScTxAO {

    // 定时器调用：每隔**同步账户流水
    public void doScTransactionSync();

    // 确认推送成功
    public void confirmPush(List<String> hashList);

    // // 分页查询交易
    // public Paginable<ScTransaction> queryTxPage(ScTxPageReq req);
}
