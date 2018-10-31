package com.cdkj.coin.bo.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.coin.bo.IEthTokenEventBO;
import com.cdkj.coin.bo.base.PaginableBOImpl;
import com.cdkj.coin.dao.IEthTokenEventDAO;
import com.cdkj.coin.domain.EthTokenEvent;
import com.cdkj.coin.ethereum.OrangeCoinToken.TransferEventResponse;

@Component
public class EthTokenEventBOImpl extends PaginableBOImpl<EthTokenEvent> implements
        IEthTokenEventBO {

    @Autowired
    private IEthTokenEventDAO tokenEventDAO;

    @Override
    public EthTokenEvent convertTokenEvent(
            TransferEventResponse transferEventResponse, String hash,
            String symbol) {
        EthTokenEvent tokenEvent = new EthTokenEvent();
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
    public void insertEventsList(List<EthTokenEvent> tokenEventList) {
        tokenEventDAO.insertEventList(tokenEventList);
    }

    @Override
    public List<EthTokenEvent> queryListByHash(String hash) {
        EthTokenEvent condition = new EthTokenEvent();
        condition.setHash(hash);
        return tokenEventDAO.selectList(condition);
    }
}
