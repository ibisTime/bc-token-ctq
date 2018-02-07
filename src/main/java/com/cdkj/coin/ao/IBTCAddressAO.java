package com.cdkj.coin.ao;

import java.util.List;

import com.cdkj.coin.bitcoin.BTCAddress;
import com.cdkj.coin.dto.req.UploadBTCAddressReq;

public interface IBTCAddressAO {

    public void uploadAddress(UploadBTCAddressReq req);

    //
    public List<BTCAddress> queryEthAddressListByAddress(String address);

    // 根据类型 分页查
    // public Paginable<BTCAddress> queryEthAddressPageByStatusList(
    // List<String> typeList, int start, int limit);

}
