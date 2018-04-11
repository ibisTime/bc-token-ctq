package com.cdkj.coin.ao;

import java.util.List;

import com.cdkj.coin.domain.TokenAddress;

public interface ITokenAddressAO {

    public void uploadAddress(String address, String contractAddress);

    public List<TokenAddress> queryTokenAddressListByAddress(String address,
            String contractAddress);

}
