package com.cdkj.coin.dao;

import java.util.List;

import com.cdkj.coin.dao.base.IBaseDAO;
import com.cdkj.coin.domain.EthTransaction;

public interface IEthTransactionDAO extends IBaseDAO<EthTransaction> {

    String NAMESPACE = IEthTransactionDAO.class.getName().concat(".");

    public void updateTxStatus(List<EthTransaction> txList);

    public void insertTxList(List<EthTransaction> txList);

    public void updateTxGasUsed(EthTransaction data);

}
