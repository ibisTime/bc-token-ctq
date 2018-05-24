package com.cdkj.coin.bo;

import java.util.List;

import com.cdkj.coin.bo.base.IPaginableBO;
import com.cdkj.coin.domain.WanAddress;
import com.cdkj.coin.dto.req.UploadWanAddressReq;
import com.cdkj.coin.dto.res.UploadWanAddressRes;

public interface IWanAddressBO extends IPaginableBO<WanAddress> {

    // 用于查询地址是否存在
    public long addressCount(String address);

    // 上传地址
    public UploadWanAddressRes uploadAddress(UploadWanAddressReq req);

    //
    public List<WanAddress> queryWanAddressList(WanAddress condition);

    // 地址 + 类型
    public int queryWanAddressCount(String address, String type);

}
