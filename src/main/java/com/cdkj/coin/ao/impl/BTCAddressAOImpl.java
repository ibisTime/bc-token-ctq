package com.cdkj.coin.ao.impl;

import com.cdkj.coin.ao.IBTCAddressAO;
import com.cdkj.coin.bo.IBTCAddressBO;
import com.cdkj.coin.domain.BTC.BTCAddress;
import com.cdkj.coin.dto.req.UploadBTCAddressReq;
import com.cdkj.coin.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BTCAddressAOImpl implements IBTCAddressAO {

    @Autowired
    private IBTCAddressBO btcAddressBO;

    @Override
    public void uploadAddress(UploadBTCAddressReq req) {

        // 首先判断 地址 + 类型 是否已存在
        int alreadyCount = this.btcAddressBO.queryAddressCount(
                req.getAddress(), req.getType());

        // 已经存在 也告知成功告知地址添加成功
        if (alreadyCount > 0) {
            return;
        }

        int count = this.btcAddressBO.uploadAddress(req.getAddress(), req.getType());
        if (count != 1) {
            throw new BizException("xn000", "上传地址失败");
        }

    }

    @Override
    public List<BTCAddress> queryEthAddressListByAddress(String address) {
        return null;
    }

}
