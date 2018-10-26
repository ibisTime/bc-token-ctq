package com.cdkj.coin.dao;

import java.util.List;

import com.cdkj.coin.dao.base.IBaseDAO;
import com.cdkj.coin.domain.TokenEvent;

public interface ITokenEventDAO extends IBaseDAO<TokenEvent> {
    String NAMESPACE = ITokenEventDAO.class.getName().concat(".");

    public void insertEventList(List<TokenEvent> tokenEventList);
}
