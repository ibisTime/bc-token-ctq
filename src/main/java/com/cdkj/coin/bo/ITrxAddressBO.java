package com.cdkj.coin.bo;

import com.cdkj.coin.bo.base.IPaginableBO;
import com.cdkj.coin.domain.TrxAddress;

import java.util.List;

public interface ITrxAddressBO extends IPaginableBO<TrxAddress> {

    // 用于查询地址是否存在
    public long addressCount(String address);

    // 上传地址
    public int uploadAddress(String address);

    //
    public List<TrxAddress> queryAddressList(TrxAddress condition);

    // 地址 + 类型
    public int queryAddressCount(String address);

}
