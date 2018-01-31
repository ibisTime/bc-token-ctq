package com.cdkj.coin.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.coin.bo.IScAddressBO;
import com.cdkj.coin.bo.base.PaginableBOImpl;
import com.cdkj.coin.core.OrderNoGenerater;
import com.cdkj.coin.dao.IScAddressDAO;
import com.cdkj.coin.domain.ScAddress;
import com.cdkj.coin.dto.req.UploadScAddressReq;
import com.cdkj.coin.exception.BizErrorCode;
import com.cdkj.coin.exception.BizException;

@Component
public class ScAddressBOImpl extends PaginableBOImpl<ScAddress> implements
        IScAddressBO {

    @Autowired
    private IScAddressDAO scAddressDAO;

    @Override
    public List<ScAddress> queryScAddressList(ScAddress condition) {
        return scAddressDAO.selectList(condition);
    }

    @Override
    public void uploadAddress(UploadScAddressReq req) {

        ScAddress address = new ScAddress();
        address.setAddress(req.getAddress());
        address.setType(req.getType());
        address.setCode(OrderNoGenerater.generateM("AD"));

        int count = this.scAddressDAO.insert(address);
        if (count <= 0) {

            throw new BizException(
                BizErrorCode.DEFAULT_ERROR_CODE.getErrorCode(), "失败");
        }

    }

    @Override
    public long addressCount(String address) {

        if (address == null || address.length() <= 0) {
            return 0;
        }

        return this.scAddressDAO.selectTotalCountByAddress(address);

    }

    @Override
    public int queryScAddressCount(String address, String type) {
        ScAddress condition = new ScAddress();
        condition.setAddress(address);
        condition.setType(type);
        return this.scAddressDAO.selectTotalCount(condition).intValue();

    }

}
