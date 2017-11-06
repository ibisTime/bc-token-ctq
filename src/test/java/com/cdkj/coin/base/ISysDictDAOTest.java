package com.cdkj.coin.base;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cdkj.coin.dao.IEthTransactionDAO;
import com.cdkj.coin.domain.EthAddress;
import com.cdkj.coin.domain.EthTransaction;
import com.cdkj.coin.enums.EPushStatus;
import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.cdkj.coin.dao.ISYSDictDAO;
import com.cdkj.coin.domain.SYSDict;

public class ISysDictDAOTest extends ADAOTest {

    @SpringBeanByType
    private ISYSDictDAO sysDictDAO;

    @SpringBeanByType
    private IEthTransactionDAO ethTransactionDAO;


    @Test
    public void insert() {
        SYSDict data = new SYSDict();
        data.setType("1");
        data.setParentKey("parentKey");
        data.setDkey("dkey");
        data.setDvalue("dvalue");

        data.setUpdater("updater");
        data.setUpdateDatetime(new Date());
        data.setRemark("remark");
        sysDictDAO.insert(data);
        logger.info("insert : {}", data.getId());

    }

    @Test
    public void select() {
        SYSDict condition = new SYSDict();
        condition.setId(1L);
        SYSDict data = sysDictDAO.select(condition);
        logger.info("select : {}", data.getId());
    }

    @Test
    public void selectList() {
        SYSDict condition = new SYSDict();
        condition.setType("0");
        condition.setParentKey("A");
        Long count = sysDictDAO.selectTotalCount(condition);
        logger.info("selectList : {}", count);
    }

    @Test
    public void testTx() {

        EthTransaction transaction = new EthTransaction();
        transaction.setHash("0x3c80d06f2de392c7a6271b8741b035269898da5ccacd8f8011491d484debee5f");

        EthTransaction transaction1 = new EthTransaction();
        transaction1.setHash("0x7f96446cac7ce566d1a482e9164d5477eeca3b995d1bd9ccc41a150d044fdc5a");
        List txList = new ArrayList();
        txList.add(transaction);
        txList.add(transaction1);


        this.ethTransactionDAO.updateTxStatus(txList);


        EthTransaction ethTransaction = new EthTransaction();
        ethTransaction.setStatus("0");
        List ls = this.ethTransactionDAO.selectList(ethTransaction);


//        EthTransaction transaction = new EthTransaction();
//        transaction.setHash("0x11eee111qq1333323211111131");
//        transaction.setNonce(new BigInteger(String.valueOf(121)));
//
//        transaction.setBlockHash("0xss");
//        transaction.setBlockNumber("0xss");
//        transaction.setTransactionIndex(new BigInteger(String.valueOf(121)));
//
//        transaction.setFrom("0xss");
//        transaction.setTo("0xss");
//
//        transaction.setValue("0xss");
//        transaction.setGasPrice("0xss");
//        transaction.setGas(new BigInteger(String.valueOf(121)));
//
//        //
//        transaction.setStatus("0");
//        transaction.setBlockCreateDatetime(new Date());
//        //--//
//        List txList = new ArrayList();
//        txList.add(transaction);
//
//        {
//
//            EthTransaction tx = new EthTransaction();
//            tx.setHash("0x1111111eee444331111132323www31");
//            tx.setNonce(new BigInteger(String.valueOf(121)));
//
//            tx.setBlockHash("0xss");
//            tx.setBlockNumber("0xss");
//            tx.setTransactionIndex(new BigInteger(String.valueOf(121)));
//
//            tx.setFrom("0xss");
//            tx.setTo("0xss");
//
//            tx.setValue("0xss");
//            tx.setGasPrice("0xss");
//            tx.setGas(new BigInteger(String.valueOf(121)));
//
//            //
//            tx.setStatus("0");
//            tx.setBlockCreateDatetime(new Date());
//            txList.add(tx);
//        }
//
//        this.ethTransactionDAO.insertTxList(txList);
//        this.ethTransactionDAO.insert(transaction);

    }

}
