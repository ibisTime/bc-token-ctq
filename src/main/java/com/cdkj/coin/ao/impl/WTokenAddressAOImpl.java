package com.cdkj.coin.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.coin.ao.IWTokenAddressAO;
import com.cdkj.coin.bo.IWTokenAddressBO;
import com.cdkj.coin.domain.WTokenAddress;
import com.cdkj.coin.exception.BizException;

@Service
public class WTokenAddressAOImpl implements IWTokenAddressAO {

    @Autowired
    private IWTokenAddressBO wtokenAddressBO;

    @Override
    public void uploadAddress(String address, String symbol) {

        // 首先判断 地址 是否已存在
        int alreadyCount = wtokenAddressBO.queryAddressCount(address, symbol);
        // 已经存在 也告知成功告知地址添加成功
        if (alreadyCount > 0) {
            return;
        }

        int count = wtokenAddressBO.uploadAddress(address, symbol);
        if (count != 1) {
            throw new BizException("xn000", "上传token地址失败");
        }
    }

    /** 
     * @see com.cdkj.coin.ao.IWTokenAddressAO#queryWTokenAddressListByAddress(java.lang.String, java.lang.String)
     */
    @Override
    public List<WTokenAddress> queryWTokenAddressListByAddress(String address,
            String symbol) {
        WTokenAddress condition = new WTokenAddress();
        condition.setAddress(address);
        condition.setSymbol(symbol);
        return wtokenAddressBO.queryAddressList(condition);
    }
}
