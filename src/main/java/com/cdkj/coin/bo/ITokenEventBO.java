package com.cdkj.coin.bo;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.coin.domain.TokenEvent;
import com.cdkj.coin.ethereum.OrangeCoinToken.TransferEventResponse;

@Component
public interface ITokenEventBO {

    public TokenEvent convertTokenEvent(
            TransferEventResponse transferEventResponse, String hash,
            String symbol);

    public void insertEventsList(List<TokenEvent> tokenEventList);

    public List<TokenEvent> queryListByHash(String hash);
}
