package com.cdkj.coin.bo;

import java.util.List;

import com.cdkj.coin.bo.base.IPaginableBO;
import com.cdkj.coin.domain.ScAddress;
import com.cdkj.coin.dto.req.UploadScAddressReq;

public interface IScAddressBO extends IPaginableBO<ScAddress> {

    // 用于查询地址是否存在
    public long addressCount(String address);

    // 上传地址
    public void uploadAddress(UploadScAddressReq req);

    //
    public List<ScAddress> queryScAddressList(ScAddress condition);

    // 地址 + 类型
    public int queryScAddressCount(String address, String type);

}
