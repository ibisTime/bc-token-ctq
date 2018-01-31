package com.cdkj.coin.dao;

import com.cdkj.coin.dao.base.IBaseDAO;
import com.cdkj.coin.domain.ScAddress;

public interface IScAddressDAO extends IBaseDAO<ScAddress> {

    String NAMESPACE = IScAddressDAO.class.getName().concat(".");

    @Override
    public Long selectTotalCount(ScAddress condition);

    public Long selectTotalCountByAddress(String address);

}
