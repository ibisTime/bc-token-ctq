package com.cdkj.coin.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.coin.ao.ITokenAddressAO;
import com.cdkj.coin.bo.ITokenAddressBO;
import com.cdkj.coin.domain.TokenAddress;
import com.cdkj.coin.exception.BizException;

@Service
public class TokenAddressAOImpl implements ITokenAddressAO {

    @Autowired
    private ITokenAddressBO tokenAddressBO;

    @Override
    public void uploadAddress(String address, String symbol) {

        // 首先判断 地址 是否已存在
        int alreadyCount = tokenAddressBO.queryAddressCount(address, symbol);
        // 已经存在 也告知成功告知地址添加成功
        if (alreadyCount > 0) {
            return;
        }

        int count = tokenAddressBO.uploadAddress(address, symbol);
        if (count != 1) {
            throw new BizException("xn000", "上传token地址失败");
        }
    }

    /** 
     * @see com.cdkj.coin.ao.ITokenAddressAO#queryTokenAddressListByAddress(java.lang.String, java.lang.String)
     */
    @Override
    public List<TokenAddress> queryTokenAddressListByAddress(String address,
            String symbol) {
        TokenAddress condition = new TokenAddress();
        condition.setAddress(address);
        condition.setSymbol(symbol);
        ;
        return tokenAddressBO.queryAddressList(condition);
    }
}
