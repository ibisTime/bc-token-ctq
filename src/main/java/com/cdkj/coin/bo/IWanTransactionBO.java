package com.cdkj.coin.bo;

import java.math.BigInteger;
import java.util.List;

import org.web3j.protocol.core.methods.response.EthBlock;

import com.cdkj.coin.bo.base.IPaginableBO;
import com.cdkj.coin.domain.WanTransaction;

public interface IWanTransactionBO extends IPaginableBO<WanTransaction> {

    // 对象转换
    public WanTransaction convertTx(EthBlock.TransactionObject tx,
            BigInteger gasUsed, BigInteger timestamp);

    // 分页查询交易
    public List<WanTransaction> queryWanTxPage(WanTransaction condition,
            int start, int limit);

    // 改变交易状态 为以推送
    public void changeTxStatusToPushed(List<String> txHashList);

    // 批量插入交易
    public void insertTxList(List<WanTransaction> txList);

    public int saveWanTransaction(WanTransaction tx);

    public List<WanTransaction> queryWanTransactionList(
            WanTransaction condition);

    public WanTransaction getWanTransaction(String hash);

    public boolean isWanTransactionExist(String hash);

    // 更新实际消耗的gas
    public void refreshGasUsed(WanTransaction tx, BigInteger gasUsed);

}
