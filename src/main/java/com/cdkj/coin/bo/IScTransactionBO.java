package com.cdkj.coin.bo;

import java.util.List;

import com.cdkj.coin.bo.base.IPaginableBO;
import com.cdkj.coin.domain.ScTransaction;

public interface IScTransactionBO extends IPaginableBO<ScTransaction> {

    public String saveScTransaction(ScTransaction data);

    public List<ScTransaction> queryScTransactionList(ScTransaction condition);

    public ScTransaction getScTransaction(String transactionid);

    public boolean isScTransactionExist(String transactionid);

    // 改变交易状态 为以推送
    public void changeTxStatusToPushed(List<String> txHashList);

}
