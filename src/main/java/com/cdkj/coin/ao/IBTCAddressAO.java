package com.cdkj.coin.ao;

import com.cdkj.coin.domain.BTC.BTCAddress;
import com.cdkj.coin.dto.req.UploadBTCAddressReq;

import java.util.List;

public interface IBTCAddressAO {

    public void uploadAddress(UploadBTCAddressReq req);

    //
    public List<BTCAddress> queryEthAddressListByAddress(String address);

    // 根据类型 分页查
//    public Paginable<BTCAddress> queryEthAddressPageByStatusList(
//            List<String> typeList, int start, int limit);

}
