package com.cdkj.coin.bo.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.coin.bo.IWanTokenEventBO;
import com.cdkj.coin.bo.base.PaginableBOImpl;
import com.cdkj.coin.dao.IWanTokenEventDAO;
import com.cdkj.coin.domain.WanTokenEvent;
import com.cdkj.coin.ethereum.OrangeCoinToken.TransferEventResponse;

@Component
public class WanTokenEventBOImpl extends PaginableBOImpl<WanTokenEvent>
        implements IWanTokenEventBO {

    @Autowired
    private IWanTokenEventDAO wanTokenEventDAO;

    @Override
    public WanTokenEvent convertTokenEvent(
            TransferEventResponse transferEventResponse, String hash,
            String symbol) {
        WanTokenEvent tokenEvent = new WanTokenEvent();
        tokenEvent.setHash(hash);
        tokenEvent.setTokenFrom(transferEventResponse.from);
        tokenEvent.setTokenTo(transferEventResponse.to);
        tokenEvent.setTokenValue(
            new BigDecimal(transferEventResponse.value.toString()));
        tokenEvent.setTokenLogIndex(transferEventResponse.log.getLogIndex());

        tokenEvent.setSymbol(symbol);
        return tokenEvent;
    }

    @Override
    public void insertEventsList(List<WanTokenEvent> tokenEventList) {
        wanTokenEventDAO.insertEventList(tokenEventList);
    }

    @Override
    public List<WanTokenEvent> queryListByHash(String hash) {
        WanTokenEvent condition = new WanTokenEvent();
        condition.setHash(hash);
        return wanTokenEventDAO.selectList(condition);
    }
}
