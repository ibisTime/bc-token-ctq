package com.cdkj.coin.ao.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.coin.ao.IWTokenContractAO;
import com.cdkj.coin.bo.IWTokenContractBO;
import com.cdkj.coin.domain.WTokenContract;
import com.cdkj.coin.exception.BizException;

@Service
public class WTokenContractAOImpl implements IWTokenContractAO {

    @Autowired
    private IWTokenContractBO wtokenContractBO;

    @Override
    public void addWTokenContract(String symbol, String contractAddress) {
        if (wtokenContractBO.isWTokenContractExist(contractAddress)
                || wtokenContractBO.isSymbolExist(symbol)) {
            return;
        }
        WTokenContract wtokenContract = new WTokenContract();
        wtokenContract.setSymbol(symbol);
        wtokenContract.setContractAddress(contractAddress);
        wtokenContract.setCreateDatetime(new Date());
        int count = wtokenContractBO.saveWTokenContract(wtokenContract);
        if (count != 1) {
            throw new BizException("xn000", "上传token合约地址失败");
        }
    }

}
