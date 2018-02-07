package com.cdkj.coin.ao;

import com.cdkj.coin.domain.BTC.BtcUtxo;

import java.util.List;

public interface IBTCTxAO {

    void confirmPush(List<BtcUtxo> utxoList);
}
