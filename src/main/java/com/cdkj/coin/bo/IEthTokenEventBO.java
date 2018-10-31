package com.cdkj.coin.bo;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.coin.domain.EthTokenEvent;
import com.cdkj.coin.ethereum.OrangeCoinToken.TransferEventResponse;

@Component
public interface IEthTokenEventBO {

    public EthTokenEvent convertTokenEvent(
            TransferEventResponse transferEventResponse, String hash,
            String symbol);

    public void insertEventsList(List<EthTokenEvent> tokenEventList);

    public List<EthTokenEvent> queryListByHash(String hash);
}
