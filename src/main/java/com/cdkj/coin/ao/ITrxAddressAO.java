package com.cdkj.coin.ao;

import com.cdkj.coin.dto.req.UploadTrxAddressReq;

public interface ITrxAddressAO {

    public void uploadAddress(UploadTrxAddressReq req);

    // 根据类型 分页查
    // public Paginable<BTCAddress> queryEthAddressPageByStatusList(
    // List<String> typeList, int start, int limit);

}
