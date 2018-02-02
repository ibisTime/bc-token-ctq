package com.cdkj.coin.dao.impl;

import com.cdkj.coin.dao.IBTCUtxoDAO;
import com.cdkj.coin.dao.base.support.AMybatisTemplate;
import com.cdkj.coin.domain.BTC.BTCUTXO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("bTCUtxoDAOImpl")
public class BTCUtxoDAOImpl extends AMybatisTemplate implements IBTCUtxoDAO {

    @Override
    public void insertUTXOList(List<BTCUTXO> utxoList) {

        super.insertBatch(NAMESPACE.concat("insertBatch"), (List) utxoList);

    }

    @Override
    public int updateByTxidAndVout(BTCUTXO update) {

        return super.update(NAMESPACE.concat("updateByPrimaryKeySelective"), update);

    }

    @Override
    public int insert(BTCUTXO data) {
        return 0;
    }

    @Override
    public int delete(BTCUTXO data) {
        return 0;
    }

    @Override
    public BTCUTXO selectByTxidAndVout(BTCUTXO condition) {

        return super.select("selectByTxidAndVout",condition,BTCUTXO.class);

    }

    @Override
    public BTCUTXO select(BTCUTXO condition) {

        return null;

    }



    @Override
    public Long selectTotalCount(BTCUTXO condition) {
        return null;
    }

    @Override
    public List<BTCUTXO> selectList(BTCUTXO condition) {

        return super.selectList(NAMESPACE.concat("selectUnPushUTXO"),condition,BTCUTXO.class);

    }

    @Override
    public List<BTCUTXO> selectList(BTCUTXO condition, int start, int count) {
        return null;
    }

}
