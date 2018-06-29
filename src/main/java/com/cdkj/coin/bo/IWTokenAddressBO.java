package com.cdkj.coin.bo;

import java.util.List;

import com.cdkj.coin.bo.base.IPaginableBO;
import com.cdkj.coin.domain.WTokenAddress;

public interface IWTokenAddressBO extends IPaginableBO<WTokenAddress> {

    // 用于查询地址是否存在
    public long addressCount(String address, String symbol);

    // 上传地址
    public int uploadAddress(String address, String symbol);

    public List<WTokenAddress> queryAddressList(WTokenAddress condition);

    // 地址 + 合约地址
    public int queryAddressCount(String address, String symbol);

}
