package com.cdkj.coin.dao;

import com.cdkj.coin.bitcoin.BTCAddress;
import com.cdkj.coin.dao.base.IBaseDAO;

public interface IBTCAddressDAO extends IBaseDAO<BTCAddress> {

    String NAMESPACE = IBTCAddressDAO.class.getName().concat(".");

    @Override
    public Long selectTotalCount(BTCAddress condition);

    public Long selectTotalCountByAddress(String address);

}
