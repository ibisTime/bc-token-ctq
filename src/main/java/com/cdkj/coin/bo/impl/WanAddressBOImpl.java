package com.cdkj.coin.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.coin.bo.IWanAddressBO;
import com.cdkj.coin.bo.base.PaginableBOImpl;
import com.cdkj.coin.core.OrderNoGenerater;
import com.cdkj.coin.dao.IWanAddressDAO;
import com.cdkj.coin.domain.WanAddress;
import com.cdkj.coin.dto.req.UploadWanAddressReq;
import com.cdkj.coin.dto.res.UploadWanAddressRes;
import com.cdkj.coin.exception.BizException;
import com.cdkj.coin.exception.EBizErrorCode;

@Component
public class WanAddressBOImpl extends PaginableBOImpl<WanAddress>
        implements IWanAddressBO {

    @Autowired
    private IWanAddressDAO wanAddressDAO;

    @Override
    public List<WanAddress> queryWanAddressList(WanAddress condition) {
        return wanAddressDAO.selectList(condition);
    }

    @Override
    public UploadWanAddressRes uploadAddress(UploadWanAddressReq req) {

        WanAddress address = new WanAddress();
        address.setAddress(req.getAddress());
        address.setType(req.getType());
        address.setCode(OrderNoGenerater.generateM("AD"));

        int count = this.wanAddressDAO.insert(address);
        if (count <= 0) {

            throw new BizException(EBizErrorCode.DEFAULT.getErrorCode(), "失败");
        }

        return new UploadWanAddressRes();
    }

    @Override
    public long addressCount(String address) {

        if (address == null || address.length() <= 0) {
            return 0;
        }

        return this.wanAddressDAO.selectTotalCountByAddress(address);

    }

    @Override
    public int queryWanAddressCount(String address, String type) {
        WanAddress condition = new WanAddress();
        condition.setAddress(address);
        condition.setType(type);
        return this.wanAddressDAO.selectTotalCount(condition).intValue();

    }

}
