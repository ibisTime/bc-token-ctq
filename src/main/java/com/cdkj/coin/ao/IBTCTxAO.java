package com.cdkj.coin.ao;

import com.cdkj.coin.domain.BTC.BTCUTXO;

import java.util.List;

public interface IBTCTxAO {

    void confirmPush(List<BTCUTXO> utxoList);
}
