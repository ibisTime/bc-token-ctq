package com.cdkj.coin.bo;

import java.util.List;

import com.cdkj.coin.bo.base.IPaginableBO;
import com.cdkj.coin.domain.TokenAddress;

public interface ITokenAddressBO extends IPaginableBO<TokenAddress> {

    // 用于查询地址是否存在
    public long addressCount(String address);

    // 上传地址
    public int uploadAddress(String address, String tokenAddress);

    public List<TokenAddress> queryAddressList(TokenAddress condition);

    // 地址 + 合约地址
    public int queryAddressCount(String address, String contractAddress);

}
