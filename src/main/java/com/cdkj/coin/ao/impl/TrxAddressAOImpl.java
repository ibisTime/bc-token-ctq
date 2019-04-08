package com.cdkj.coin.ao.impl;

import com.cdkj.coin.ao.ITrxAddressAO;
import com.cdkj.coin.bo.ITrxAddressBO;
import com.cdkj.coin.dto.req.UploadTrxAddressReq;
import com.cdkj.coin.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrxAddressAOImpl implements ITrxAddressAO {

    @Autowired
    private ITrxAddressBO trxAddressBO;

    @Override
    public void uploadAddress(UploadTrxAddressReq req) {

        // 首先判断 地址 是否已存在
        int alreadyCount = this.trxAddressBO
            .queryAddressCount(req.getAddress());

        // 已经存在 也告知成功告知地址添加成功
        if (alreadyCount > 0) {
            return;
        }

        int count = this.trxAddressBO.uploadAddress(req.getAddress());
        if (count != 1) {
            throw new BizException("xn000", "上传TRX地址失败");
        }

    }
}
