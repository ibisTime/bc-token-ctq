package com.cdkj.coin.dao;

import java.util.List;

import com.cdkj.coin.dao.base.IBaseDAO;
import com.cdkj.coin.domain.WanTokenEvent;

public interface IWanTokenEventDAO extends IBaseDAO<WanTokenEvent> {
    String NAMESPACE = IWanTokenEventDAO.class.getName().concat(".");

    public void insertEventList(List<WanTokenEvent> tokenEventList);
}
