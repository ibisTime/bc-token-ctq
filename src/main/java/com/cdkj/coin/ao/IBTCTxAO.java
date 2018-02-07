package com.cdkj.coin.ao;

import com.cdkj.coin.bitcoin.BtcUtxo;

import java.util.List;

public interface IBTCTxAO {

    void confirmPush(List<BtcUtxo> utxoList);
}
