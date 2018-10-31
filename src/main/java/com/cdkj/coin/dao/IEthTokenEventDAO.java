package com.cdkj.coin.dao;

import java.util.List;

import com.cdkj.coin.dao.base.IBaseDAO;
import com.cdkj.coin.domain.EthTokenEvent;

public interface IEthTokenEventDAO extends IBaseDAO<EthTokenEvent> {
    String NAMESPACE = IEthTokenEventDAO.class.getName().concat(".");

    public void insertEventList(List<EthTokenEvent> tokenEventList);
}
