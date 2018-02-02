package com.cdkj.coin.dao;

import com.cdkj.coin.dao.base.IBaseDAO;
import com.cdkj.coin.domain.BTC.BTCUTXO;
import com.cdkj.coin.domain.EthAddress;

import java.util.List;

public interface IBTCUtxoDAO extends IBaseDAO<BTCUTXO> {

    String NAMESPACE = IBTCUtxoDAO.class.getName().concat(".");

    void insertUTXOList(List< BTCUTXO> utxoList);
    int updateByTxidAndVout(BTCUTXO update);
    BTCUTXO selectByTxidAndVout(BTCUTXO condition);

}
