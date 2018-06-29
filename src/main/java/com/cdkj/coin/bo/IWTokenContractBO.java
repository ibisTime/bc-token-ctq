package com.cdkj.coin.bo;

import java.util.List;

import com.cdkj.coin.bo.base.IPaginableBO;
import com.cdkj.coin.domain.WTokenContract;

public interface IWTokenContractBO extends IPaginableBO<WTokenContract> {

    public boolean isSymbolExist(String symbol);

    public boolean isWTokenContractExist(String contractAddress);

    public int saveWTokenContract(WTokenContract data);

    public List<WTokenContract> queryWTokenContractList(
            WTokenContract condition);

    public WTokenContract getWTokenContract(String contractAddress);

}
