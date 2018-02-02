package com.cdkj.coin.bo;

import com.cdkj.coin.bo.base.IPaginableBO;
import com.cdkj.coin.domain.BTC.BTCUTXO;

import java.util.List;

public interface IBTCUtxoBO extends IPaginableBO<BTCUTXO> {

    void insertUTXOList(List<BTCUTXO> list);
    int update(String txid,int vout,String status);
    List<BTCUTXO> selectUnPush();
    BTCUTXO select(String txid, Integer vout);

}
