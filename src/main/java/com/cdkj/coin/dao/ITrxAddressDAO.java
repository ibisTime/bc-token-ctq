package com.cdkj.coin.dao;

import com.cdkj.coin.dao.base.IBaseDAO;
import com.cdkj.coin.domain.TrxAddress;

public interface ITrxAddressDAO extends IBaseDAO<TrxAddress> {

    String NAMESPACE = ITrxAddressDAO.class.getName().concat(".");

    @Override
    public Long selectTotalCount(TrxAddress condition);

    public Long selectTotalCountByAddress(String address);

}
