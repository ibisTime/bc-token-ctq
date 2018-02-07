package com.cdkj.coin.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.coin.ao.IBTCAddressAO;
import com.cdkj.coin.bitcoin.BTCAddress;
import com.cdkj.coin.bo.IBTCAddressBO;
import com.cdkj.coin.dto.req.UploadBTCAddressReq;
import com.cdkj.coin.exception.BizException;

@Service
public class BTCAddressAOImpl implements IBTCAddressAO {

    @Autowired
    private IBTCAddressBO btcAddressBO;

    @Override
    public void uploadAddress(UploadBTCAddressReq req) {

        // 首先判断 地址 是否已存在
        int alreadyCount = this.btcAddressBO
            .queryAddressCount(req.getAddress());

        // 已经存在 也告知成功告知地址添加成功
        if (alreadyCount > 0) {
            return;
        }

        int count = this.btcAddressBO.uploadAddress(req.getAddress());
        if (count != 1) {
            throw new BizException("xn000", "上传BTC地址失败");
        }

    }

    @Override
    public List<BTCAddress> queryEthAddressListByAddress(String address) {
        return null;
    }

}
