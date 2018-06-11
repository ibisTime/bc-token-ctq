package com.cdkj.coin.ao;

import java.util.List;

import com.cdkj.coin.bo.base.Paginable;
import com.cdkj.coin.domain.WanTransaction;
import com.cdkj.coin.dto.req.WanTxPageReq;

/**
 * Created by tianlei on 2017/十一月/02.
 */
public interface IWanTxAO {

    // 定时器调用：每隔**同步账户流水
    public void doWanTransactionSync();

    // 确认推送成功
    public Object confirmPush(List<String> hashList);

    // 分页查询交易
    public Paginable<WanTransaction> queryTxPage(WanTxPageReq req);

    // 分页查询广播记录
    public Paginable<WanTransaction> queryWanTransactionPage(int start,
            int limit, WanTransaction condition);
}
