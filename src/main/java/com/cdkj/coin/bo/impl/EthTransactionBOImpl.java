package com.cdkj.coin.bo.impl;

import java.io.File;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cdkj.coin.enums.EPushStatus;
import com.cdkj.coin.exception.BizErrorCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.RawTransaction;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

import com.cdkj.coin.bo.IEthTransactionBO;
import com.cdkj.coin.bo.base.PaginableBOImpl;
import com.cdkj.coin.dao.IEthTransactionDAO;
import com.cdkj.coin.domain.EthTransaction;
import com.cdkj.coin.exception.BizException;

@Component
public class EthTransactionBOImpl extends PaginableBOImpl<EthTransaction>
        implements IEthTransactionBO {

    private static Web3j web3j = Web3j.build(new HttpService(
        "https://mainnet.infura.io/ZJR3JJlmLyf5mg4A9UxA"));;

    @Autowired
    private IEthTransactionDAO ethTransactionDAO;

    @Override
    public EthTransaction convertTx(EthBlock.TransactionObject tx,BigInteger timestamp)   {


        if (tx == null || timestamp == null) return null;

        EthTransaction transaction = new EthTransaction();
        transaction.setHash(tx.getHash());
        transaction.setNonce(tx.getNonce());

        transaction.setBlockHash(tx.getBlockHash());
        transaction.setBlockNumber(tx.getBlockNumber().toString());
        transaction.setTransactionIndex(tx.getTransactionIndex());

        transaction.setFrom(tx.getFrom());
        transaction.setTo(tx.getTo());

        transaction.setValue(tx.getValue().toString());
        transaction.setGasPrice(tx.getGasPrice().toString());
        transaction.setGas(tx.getGas());

        //
        transaction.setStatus(EPushStatus.UN_PUSH.getCode());

        transaction.setBlockCreateDatetime(new Date(timestamp.longValue() * 1000));

        return transaction;
    }

    @Override
    public int saveEthTransaction(EthTransaction tx) {

       return this.ethTransactionDAO.insert(tx);

    }

    @Override
    public void saveEthTransactionList(List<EthTransaction> txList) {

         this.ethTransactionDAO.insertTxList(txList);

    }

    @Override
    public List<EthTransaction> queryEthTransactionList(EthTransaction condition) {
        return ethTransactionDAO.selectList(condition);
    }

    @Override
    public List<EthTransaction> queryEthTxPage(EthTransaction condition, int start, int limit) {

        return ethTransactionDAO.selectList(condition, start, limit);
    }

    @Override
    public void changeTxStatusToPushed(List<String> txHashList) {

        List<EthTransaction> txList = new ArrayList();

        txHashList.forEach(hash -> {

            EthTransaction ethTransaction = new EthTransaction();
            ethTransaction.setHash(hash);
            txList.add(ethTransaction);
        });

      this.ethTransactionDAO.updateTxStatus(txList);


    }

    @Override
    public void insertTxList(List<EthTransaction> txList) {
        this.ethTransactionDAO.insertTxList(txList);
    }

    @Override
    public EthTransaction getEthTransaction(String hash) {
        EthTransaction data = null;
        if (StringUtils.isNotBlank(hash)) {
            EthTransaction condition = new EthTransaction();
            condition.setHash(hash);
            data = ethTransactionDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "以太坊交易记录不存在");
            }
        }
        return data;
    }

}
