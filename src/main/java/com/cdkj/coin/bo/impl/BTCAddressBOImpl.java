package com.cdkj.coin.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.coin.bitcoin.BTCAddress;
import com.cdkj.coin.bo.IBTCAddressBO;
import com.cdkj.coin.bo.base.PaginableBOImpl;
import com.cdkj.coin.core.OrderNoGenerater;
import com.cdkj.coin.dao.IBTCAddressDAO;

@Component
public class BTCAddressBOImpl extends PaginableBOImpl<BTCAddress>
        implements IBTCAddressBO {

    @Autowired
    private IBTCAddressDAO btcAddressDAO;

    @Override
    public List<BTCAddress> queryAddressList(BTCAddress condition) {
        return btcAddressDAO.selectList(condition);
    }

    @Override
    public int uploadAddress(String address) {

        BTCAddress btcAddress = new BTCAddress();
        btcAddress.setCode(OrderNoGenerater.generateM("AD"));
        btcAddress.setAddress(address);
        int count = this.btcAddressDAO.insert(btcAddress);
        return count;

    }

    @Override
    public long addressCount(String address) {

        if (address == null || address.length() <= 0) {
            return 0;
        }

        return this.btcAddressDAO.selectTotalCountByAddress(address);

    }

    @Override
    public int queryAddressCount(String address) {

        BTCAddress condition = new BTCAddress();
        condition.setAddress(address);
        return this.btcAddressDAO.selectTotalCount(condition).intValue();

    }

}
