package com.cdkj.coin.bo;

import java.util.List;

import com.cdkj.coin.bo.base.IPaginableBO;
import com.cdkj.coin.domain.TokenContract;

public interface ITokenContractBO extends IPaginableBO<TokenContract> {

    public boolean isSymbolExist(String symbol);

    public boolean isTokenContractExist(String contractAddress);

    public int saveTokenContract(TokenContract data);

    public List<TokenContract> queryTokenContractList(TokenContract condition);

    public TokenContract getTokenContract(String code);

}
