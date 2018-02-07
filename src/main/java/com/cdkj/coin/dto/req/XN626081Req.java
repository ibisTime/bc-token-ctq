package com.cdkj.coin.dto.req;

import com.cdkj.coin.domain.BTC.BtcUtxo;

import javax.validation.constraints.NotNull;
import java.util.List;

public class XN626081Req {

    @NotNull
    List<BtcUtxo> utxoList;

    public List<BtcUtxo> getUtxoList() {
        return utxoList;
    }

    public void setUtxoList(List<BtcUtxo> utxoList) {
        this.utxoList = utxoList;
    }
}
