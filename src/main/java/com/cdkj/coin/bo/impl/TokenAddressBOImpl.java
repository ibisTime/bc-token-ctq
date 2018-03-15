package com.cdkj.coin.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.coin.bo.ITokenAddressBO;
import com.cdkj.coin.bo.base.PaginableBOImpl;
import com.cdkj.coin.core.OrderNoGenerater;
import com.cdkj.coin.dao.ITokenAddressDAO;
import com.cdkj.coin.domain.TokenAddress;

@Component
public class TokenAddressBOImpl extends PaginableBOImpl<TokenAddress>
        implements ITokenAddressBO {

    @Autowired
    private ITokenAddressDAO tokenAddressDAO;

    @Override
    public List<TokenAddress> queryAddressList(TokenAddress condition) {
        return tokenAddressDAO.selectList(condition);
    }

    @Override
    public int uploadAddress(String address, String symbol) {
        TokenAddress tokenAddress = new TokenAddress();
        tokenAddress.setCode(OrderNoGenerater.generateM("AD"));
        tokenAddress.setAddress(address);
        tokenAddress.setSymbol(symbol);
        tokenAddress.setCreateDatetime(new Date());
        int count = tokenAddressDAO.insert(tokenAddress);
        return count;
    }

    @Override
    public long addressCount(String address) {
        long count = 0;
        if (StringUtils.isNotBlank(address)) {
            count = tokenAddressDAO.selectTotalCountByAddress(address);
        }
        return count;
    }

    @Override
    public int queryAddressCount(String address, String symbol) {
        TokenAddress condition = new TokenAddress();
        condition.setAddress(address);
        condition.setSymbol(symbol);
        return this.tokenAddressDAO.selectTotalCount(condition).intValue();
    }
}
