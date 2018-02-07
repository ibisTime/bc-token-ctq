package com.cdkj.coin.bo;

import java.util.List;

import com.cdkj.coin.bo.base.IPaginableBO;
import com.cdkj.coin.domain.bitcoin.BtcUtxo;
import com.cdkj.coin.enums.EBTCUtxoStatus;

public interface IBtcUtxoBO extends IPaginableBO<BtcUtxo> {

    public boolean isBtcUtxoExist(String txid, Integer vout);

    public int saveBtcUtxo(BtcUtxo data);

    public int refreshStatus(BtcUtxo data, EBTCUtxoStatus status);

    public List<BtcUtxo> queryBtcUtxoList(BtcUtxo condition);

    public BtcUtxo getBtcUtxo(String txid, Integer vout);

    public List<BtcUtxo> selectUnPush();

}
