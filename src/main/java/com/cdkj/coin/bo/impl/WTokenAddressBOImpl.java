package com.cdkj.coin.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.coin.bo.IWTokenAddressBO;
import com.cdkj.coin.bo.base.PaginableBOImpl;
import com.cdkj.coin.core.OrderNoGenerater;
import com.cdkj.coin.dao.IWTokenAddressDAO;
import com.cdkj.coin.domain.WTokenAddress;

@Component
public class WTokenAddressBOImpl extends PaginableBOImpl<WTokenAddress>
        implements IWTokenAddressBO {

    @Autowired
    private IWTokenAddressDAO tokenAddressDAO;

    @Override
    public List<WTokenAddress> queryAddressList(WTokenAddress condition) {
        return tokenAddressDAO.selectList(condition);
    }

    @Override
    public int uploadAddress(String address, String symbol) {
        WTokenAddress tokenAddress = new WTokenAddress();
        tokenAddress.setCode(OrderNoGenerater.generateM("AD"));
        tokenAddress.setAddress(address);
        tokenAddress.setSymbol(symbol);
        tokenAddress.setCreateDatetime(new Date());
        int count = tokenAddressDAO.insert(tokenAddress);
        return count;
    }

    @Override
    public long addressCount(String address, String symbol) {
        long count = 0;
        if (StringUtils.isNotBlank(address)) {
            count = tokenAddressDAO.selectTotalCountByAddress(address, symbol);
        }
        return count;
    }

    @Override
    public int queryAddressCount(String address, String symbol) {
        WTokenAddress condition = new WTokenAddress();
        condition.setAddress(address);
        condition.setSymbol(symbol);
        return this.tokenAddressDAO.selectTotalCount(condition).intValue();
    }
}
