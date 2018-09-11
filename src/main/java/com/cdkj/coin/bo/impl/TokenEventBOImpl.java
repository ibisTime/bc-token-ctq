package com.cdkj.coin.bo.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.coin.bo.ITokenEventBO;
import com.cdkj.coin.bo.base.PaginableBOImpl;
import com.cdkj.coin.dao.ITokenEventDAO;
import com.cdkj.coin.domain.TokenEvent;
import com.cdkj.coin.ethereum.OrangeCoinToken.TransferEventResponse;

@Component
public class TokenEventBOImpl extends PaginableBOImpl<TokenEvent> implements
        ITokenEventBO {

    @Autowired
    private ITokenEventDAO tokenEventDAO;

    @Override
    public TokenEvent convertTokenEvent(
            TransferEventResponse transferEventResponse, String hash,
            String symbol) {
        TokenEvent tokenEvent = new TokenEvent();
        tokenEvent.setHash(hash);
        tokenEvent.setTokenFrom(transferEventResponse.from);
        tokenEvent.setTokenTo(transferEventResponse.to);
        tokenEvent.setTokenValue(new BigDecimal(transferEventResponse.value
            .toString()));
        tokenEvent.setTokenLogIndex(transferEventResponse.log.getLogIndex());

        tokenEvent.setSymbol(symbol);
        return tokenEvent;
    }

    @Override
    public void insertEventsList(List<TokenEvent> tokenEventList) {
        tokenEventDAO.insertEventList(tokenEventList);
    }

    @Override
    public List<TokenEvent> queryListByHash(String hash) {
        TokenEvent condition = new TokenEvent();
        condition.setHash(hash);
        return tokenEventDAO.selectList(condition);
    }
}
