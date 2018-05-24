package com.cdkj.coin.dao;

import com.cdkj.coin.dao.base.IBaseDAO;
import com.cdkj.coin.domain.WanAddress;

public interface IWanAddressDAO extends IBaseDAO<WanAddress> {

    String NAMESPACE = IWanAddressDAO.class.getName().concat(".");

    @Override
    public Long selectTotalCount(WanAddress condition);

    public Long selectTotalCountByAddress(String address);

}
