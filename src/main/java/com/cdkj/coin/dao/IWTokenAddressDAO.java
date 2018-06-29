package com.cdkj.coin.dao;

import com.cdkj.coin.dao.base.IBaseDAO;
import com.cdkj.coin.domain.WTokenAddress;

public interface IWTokenAddressDAO extends IBaseDAO<WTokenAddress> {

    String NAMESPACE = IWTokenAddressDAO.class.getName().concat(".");

    public Long selectTotalCountByAddress(String address, String symbol);

}
