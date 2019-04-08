package com.cdkj.coin.bo.impl;

import com.cdkj.coin.bo.ITrxAddressBO;
import com.cdkj.coin.bo.base.PaginableBOImpl;
import com.cdkj.coin.core.OrderNoGenerater;
import com.cdkj.coin.dao.ITrxAddressDAO;
import com.cdkj.coin.domain.TrxAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrxAddressBOImpl extends PaginableBOImpl<TrxAddress>
        implements ITrxAddressBO {

    @Autowired
    private ITrxAddressDAO trxAddressDAO;

    @Override
    public List<TrxAddress> queryAddressList(TrxAddress condition) {
        return trxAddressDAO.selectList(condition);
    }

    @Override
    public int uploadAddress(String address) {

        TrxAddress trxAddress = new TrxAddress();
        trxAddress.setCode(OrderNoGenerater.generateM("AD"));
        trxAddress.setAddress(address);
        int count = this.trxAddressDAO.insert(trxAddress);
        return count;

    }

    @Override
    public long addressCount(String address) {

        if (address == null || address.length() <= 0) {
            return 0;
        }

        return this.trxAddressDAO.selectTotalCountByAddress(address);

    }

    @Override
    public int queryAddressCount(String address) {

        TrxAddress condition = new TrxAddress();
        condition.setAddress(address);
        return this.trxAddressDAO.selectTotalCount(condition).intValue();

    }

}
