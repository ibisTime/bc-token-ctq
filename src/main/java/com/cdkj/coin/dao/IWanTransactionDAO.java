package com.cdkj.coin.dao;

import java.util.List;

import com.cdkj.coin.dao.base.IBaseDAO;
import com.cdkj.coin.domain.WanTransaction;

public interface IWanTransactionDAO extends IBaseDAO<WanTransaction> {
    String NAMESPACE = IWanTransactionDAO.class.getName().concat(".");

    public void updateTxStatus(List<WanTransaction> txList);

    public void insertTxList(List<WanTransaction> txList);

}
