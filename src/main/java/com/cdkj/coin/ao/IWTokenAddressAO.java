package com.cdkj.coin.ao;

import java.util.List;

import com.cdkj.coin.domain.WTokenAddress;

public interface IWTokenAddressAO {

    public void uploadAddress(String address, String contractAddress);

    public List<WTokenAddress> queryWTokenAddressListByAddress(String address,
            String contractAddress);

}
