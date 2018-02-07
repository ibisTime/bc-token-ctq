package com.cdkj.coin.dao;

import com.cdkj.coin.bitcoin.BtcUtxo;
import com.cdkj.coin.dao.base.IBaseDAO;

public interface IBtcUtxoDAO extends IBaseDAO<BtcUtxo> {
    String NAMESPACE = IBtcUtxoDAO.class.getName().concat(".");

    public int updateStatus(BtcUtxo data);
}
