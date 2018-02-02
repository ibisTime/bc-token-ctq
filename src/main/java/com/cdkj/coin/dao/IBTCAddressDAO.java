package com.cdkj.coin.dao;

import com.cdkj.coin.dao.base.IBaseDAO;
import com.cdkj.coin.domain.BTC.BTCAddress;

public interface IBTCAddressDAO extends IBaseDAO<BTCAddress> {

    String NAMESPACE = IEthAddressDAO.class.getName().concat(".");

    @Override
    public Long selectTotalCount(BTCAddress condition);

    public Long selectTotalCountByAddress(String address);

}

