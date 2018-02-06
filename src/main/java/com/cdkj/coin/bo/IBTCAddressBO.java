package com.cdkj.coin.bo;

import java.util.List;

import com.cdkj.coin.bo.base.IPaginableBO;
import com.cdkj.coin.domain.BTC.BTCAddress;

public interface IBTCAddressBO extends IPaginableBO<BTCAddress> {

    // 用于查询地址是否存在
    public long addressCount(String address);

    // 上传地址
    public int uploadAddress(String address);

    //
    public List<BTCAddress> queryAddressList(BTCAddress condition);

    // 地址 + 类型
    public int queryAddressCount(String address);

}
