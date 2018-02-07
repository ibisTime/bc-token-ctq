package com.cdkj.coin.dao;

import com.cdkj.coin.dao.base.IBaseDAO;
import com.cdkj.coin.domain.BTC.BtcUtxo;

public interface IBtcUtxoDAO extends IBaseDAO<BtcUtxo> {
    String NAMESPACE = IBtcUtxoDAO.class.getName().concat(".");

    public int updateStatus(BtcUtxo data);
}
