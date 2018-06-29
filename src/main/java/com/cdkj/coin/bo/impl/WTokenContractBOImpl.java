package com.cdkj.coin.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.coin.bo.IWTokenContractBO;
import com.cdkj.coin.bo.base.PaginableBOImpl;
import com.cdkj.coin.dao.IWTokenContractDAO;
import com.cdkj.coin.domain.WTokenContract;
import com.cdkj.coin.exception.BizException;

@Component
public class WTokenContractBOImpl extends PaginableBOImpl<WTokenContract>
        implements IWTokenContractBO {

    @Autowired
    private IWTokenContractDAO wtokenContractDAO;

    @Override
    public boolean isWTokenContractExist(String contractAddress) {
        WTokenContract condition = new WTokenContract();
        condition.setContractAddress(contractAddress);
        if (wtokenContractDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isSymbolExist(String symbol) {
        WTokenContract condition = new WTokenContract();
        condition.setSymbol(symbol);
        if (wtokenContractDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public int saveWTokenContract(WTokenContract data) {
        int count = 0;
        if (data != null) {
            count = wtokenContractDAO.insert(data);
        }
        return count;
    }

    @Override
    public List<WTokenContract> queryWTokenContractList(
            WTokenContract condition) {
        return wtokenContractDAO.selectList(condition);
    }

    @Override
    public WTokenContract getWTokenContract(String contractAddress) {
        WTokenContract data = null;
        if (StringUtils.isNotBlank(contractAddress)) {
            WTokenContract condition = new WTokenContract();
            condition.setContractAddress(contractAddress);
            data = wtokenContractDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "币种合约不存在");
            }
        }
        return data;
    }

}
