package com.cdkj.coin.ao;

import java.util.List;

/**
 * @author: haiqingzheng 
 * @since: 2018年6月29日 下午7:19:23 
 * @history:
 */
public interface IWTokenTxAO {

    // 定时器调用：每隔**同步账户流水
    public void doWTokenTransactionSync();

    // 确认推送成功
    public Object confirmPush(List<Long> idList);

}
