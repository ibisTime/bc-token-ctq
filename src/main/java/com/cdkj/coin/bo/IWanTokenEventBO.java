package com.cdkj.coin.bo;

import java.util.List;

import com.cdkj.coin.domain.WanTokenEvent;
import com.cdkj.coin.ethereum.OrangeCoinToken.TransferEventResponse;

public interface IWanTokenEventBO {

    public WanTokenEvent convertTokenEvent(
            TransferEventResponse transferEventResponse, String hash,
            String symbol);

    public void insertEventsList(List<WanTokenEvent> tokenEventList);

    public List<WanTokenEvent> queryListByHash(String hash);
}
