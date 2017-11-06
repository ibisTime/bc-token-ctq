package com.cdkj.coin.bo.impl;

import java.util.List;

import com.cdkj.coin.core.OrderNoGenerater;
import com.cdkj.coin.dto.req.UploadEthAddressReq;
import com.cdkj.coin.dto.res.UploadEthAddressRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.coin.bo.IEthAddressBO;
import com.cdkj.coin.bo.base.PaginableBOImpl;
import com.cdkj.coin.dao.IEthAddressDAO;
import com.cdkj.coin.domain.EthAddress;
import com.cdkj.coin.exception.BizException;

@Component
public class EthAddressBOImpl extends PaginableBOImpl<EthAddress> implements
        IEthAddressBO {

    @Autowired
    private IEthAddressDAO ethAddressDAO;

    @Override
    public List<EthAddress> queryEthAddressList(EthAddress condition) {
        return ethAddressDAO.selectList(condition);
    }

    @Override
    public UploadEthAddressRes uploadAddress(UploadEthAddressReq req) {

        EthAddress address = new EthAddress();
        address.setAddress(req.getAddress());
        address.setType(req.getType());
        address.setCode(OrderNoGenerater.generateM("AD"));

        int count =  this.ethAddressDAO.insert(address);
        if (count == 0) {
            throw new BizException("12","失败");
        }

        return new UploadEthAddressRes();
    }

    @Override
    public long addressCount(String address) {

        //
        EthAddress selectedAddress = new EthAddress();
        selectedAddress.setAddress(address);
        return  this.ethAddressDAO.selectTotalCount(selectedAddress);

    }

    @Override
    public int queryEthAddressCount(EthAddress condition) {

        return this.ethAddressDAO.selectTotalCount(condition).intValue();

    }

}
