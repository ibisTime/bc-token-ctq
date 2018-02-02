package com.cdkj.coin.dto.req;

import com.cdkj.coin.domain.BTC.BTCUTXO;

import javax.validation.constraints.NotNull;
import java.util.List;

public class XN626081Req {

    @NotNull
    List<BTCUTXO> utxoList;

    public List<BTCUTXO> getUtxoList() {
        return utxoList;
    }

    public void setUtxoList(List<BTCUTXO> utxoList) {
        this.utxoList = utxoList;
    }
}
