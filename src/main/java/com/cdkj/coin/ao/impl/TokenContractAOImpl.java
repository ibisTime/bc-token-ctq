package com.cdkj.coin.ao.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.coin.ao.ITokenContractAO;
import com.cdkj.coin.bo.ITokenContractBO;
import com.cdkj.coin.domain.TokenContract;
import com.cdkj.coin.exception.BizException;

@Service
public class TokenContractAOImpl implements ITokenContractAO {

    @Autowired
    private ITokenContractBO tokenContractBO;

    @Override
    public void addTokenContract(String symbol, String contractAddress) {
        if (tokenContractBO.isTokenContractExist(contractAddress)
                || tokenContractBO.isSymbolExist(symbol)) {
            return;
        }
        TokenContract tokenContract = new TokenContract();
        tokenContract.setSymbol(symbol);
        tokenContract.setContractAddress(contractAddress);
        tokenContract.setCreateDatetime(new Date());
        int count = tokenContractBO.saveTokenContract(tokenContract);
        if (count != 1) {
            throw new BizException("xn000", "上传token合约地址失败");
        }
    }

}
