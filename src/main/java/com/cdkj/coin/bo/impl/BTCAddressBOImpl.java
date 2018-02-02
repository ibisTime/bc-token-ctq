package com.cdkj.coin.bo.impl;

import com.cdkj.coin.bo.IBTCAddressBO;
import com.cdkj.coin.bo.base.PaginableBOImpl;
import com.cdkj.coin.dao.IBTCAddressDAO;
import com.cdkj.coin.domain.BTC.BTCAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BTCAddressBOImpl extends PaginableBOImpl<BTCAddress> implements
        IBTCAddressBO {

    @Autowired
    private IBTCAddressDAO btcAddressDAO;

    @Override
    public List<BTCAddress> queryAddressList(BTCAddress condition) {
        return btcAddressDAO.selectList(condition);
    }

    @Override
    public int uploadAddress(String address, String type) {

        BTCAddress btcAddress = new BTCAddress();
        btcAddress.setAddress(address);
        btcAddress.setType(type);
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
    public int queryAddressCount(String address, String type) {

        BTCAddress condition = new BTCAddress();
        condition.setAddress(address);
        condition.setType(type);
        return this.btcAddressDAO.selectTotalCount(condition).intValue();

    }

}