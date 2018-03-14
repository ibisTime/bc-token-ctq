package com.cdkj.coin.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.coin.bo.ITokenContractBO;
import com.cdkj.coin.bo.base.PaginableBOImpl;
import com.cdkj.coin.dao.ITokenContractDAO;
import com.cdkj.coin.domain.TokenContract;
import com.cdkj.coin.exception.BizException;

@Component
public class TokenContractBOImpl extends PaginableBOImpl<TokenContract>
        implements ITokenContractBO {

    @Autowired
    private ITokenContractDAO tokenContractDAO;

    @Override
    public boolean isTokenContractExist(String contractAddress) {
        TokenContract condition = new TokenContract();
        condition.setContractAddress(contractAddress);
        if (tokenContractDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isSymbolExist(String symbol) {
        TokenContract condition = new TokenContract();
        condition.setSymbol(symbol);
        if (tokenContractDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public int saveTokenContract(TokenContract data) {
        int count = 0;
        if (data != null) {
            count = tokenContractDAO.insert(data);
        }
        return count;
    }

    @Override
    public List<TokenContract> queryTokenContractList(TokenContract condition) {
        return tokenContractDAO.selectList(condition);
    }

    @Override
    public TokenContract getTokenContract(String symbol) {
        TokenContract data = null;
        if (StringUtils.isNotBlank(symbol)) {
            TokenContract condition = new TokenContract();
            condition.setSymbol(symbol);
            data = tokenContractDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "币种合约不存在");
            }
        }
        return data;
    }

}
