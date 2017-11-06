package com.cdkj.coin.bo;

import java.util.List;

import com.cdkj.coin.bo.base.IPaginableBO;
import com.cdkj.coin.bo.base.Paginable;
import com.cdkj.coin.domain.EthAddress;
import com.cdkj.coin.dto.req.UploadEthAddressReq;
import com.cdkj.coin.dto.res.UploadEthAddressRes;
import com.cdkj.coin.enums.EEthAddressType;

public interface IEthAddressBO extends IPaginableBO<EthAddress> {



    //用于查询地址是否存在
    public long addressCount(String address);

    //上传地址
    public UploadEthAddressRes uploadAddress(UploadEthAddressReq req);

    //
    public int saveEthAddress(EEthAddressType type, String userId,
            String address, String password);

    //
    public List<EthAddress> queryEthAddressList(EthAddress condition);

    //
    public int queryEthAddressCount(EthAddress condition);

}
