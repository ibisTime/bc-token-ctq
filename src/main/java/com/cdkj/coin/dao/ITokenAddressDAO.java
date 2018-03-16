package com.cdkj.coin.dao;

import com.cdkj.coin.dao.base.IBaseDAO;
import com.cdkj.coin.domain.TokenAddress;

public interface ITokenAddressDAO extends IBaseDAO<TokenAddress> {

    String NAMESPACE = ITokenAddressDAO.class.getName().concat(".");

    public Long selectTotalCountByAddress(String address, String symbol);

}
