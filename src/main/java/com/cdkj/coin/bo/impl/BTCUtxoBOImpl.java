package com.cdkj.coin.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.coin.bo.IBTCUtxoBO;
import com.cdkj.coin.bo.base.Paginable;
import com.cdkj.coin.bo.base.PaginableBOImpl;
import com.cdkj.coin.dao.IBTCUtxoDAO;
import com.cdkj.coin.domain.BTC.BTCUTXO;

@Component
public class BTCUtxoBOImpl extends PaginableBOImpl<BTCUTXO> implements
        IBTCUtxoBO {

    @Autowired
    IBTCUtxoDAO btcUtxoDAO;

    @Override
    public void insertUTXOList(List<BTCUTXO> list) {

        this.btcUtxoDAO.insertUTXOList(list);

    }

    @Override
    public int update(String txid, int vout, String status) {

        BTCUTXO updateCondition = new BTCUTXO();
        updateCondition.setTxid(txid);
        updateCondition.setVout(vout);
        updateCondition.setStatus(status);

        return this.btcUtxoDAO.updateByTxidAndVout(updateCondition);

    }

    @Override
    public List<BTCUTXO> selectUnPush() {

        // 这里 条件 写到sql里面去了
        return this.btcUtxoDAO.selectList(null);

    }

    @Override
    public BTCUTXO select(String txid, Integer vout) {

        BTCUTXO condition = new BTCUTXO();
        condition.setTxid(txid);
        condition.setVout(vout);
        return this.btcUtxoDAO.select(condition);

    }

    @Override
    public long getTotalCount(BTCUTXO condition) {
        return 0;
    }

    /**
     * @param start
     * @param condition
     * @return
     */
    @Override
    public Paginable<BTCUTXO> getPaginable(int start, BTCUTXO condition) {
        return null;
    }

    /**
     * 获取分页结果
     *
     * @param start
     * @param pageSize
     * @param condition
     * @return
     */
    @Override
    public Paginable<BTCUTXO> getPaginable(int start, int pageSize,
            BTCUTXO condition) {
        return null;
    }
}
