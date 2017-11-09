package com.cdkj.coin.dao.impl;

import com.cdkj.coin.common.PropertiesUtil;
import org.junit.Test;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;

import static org.junit.Assert.*;

/**
 * Created by tianlei on 2017/十一月/02.
 */
public class EthTransactionDAOImplTest {
    @Test
    public void insert() throws Exception {

//        String ETH_URL = PropertiesUtil.Config.ETH_URL;

        String ETH_URL = "http://116.62.6.195:8545";
       Web3j web3j = Web3j.build(new HttpService(ETH_URL));
     BigInteger balance = web3j.ethGetBalance("0xA4ed879Fe88EF7a17665817DCdE79aeE33a3d2f5", DefaultBlockParameterName.LATEST).send().getBalance();

//     web3j.ethEstimateGas()


       Transaction tx = new Transaction("0x09645405040acd6a22042e6995945ddbd7cc489a",BigInteger.valueOf(1000),BigInteger.valueOf(1000),BigInteger.valueOf(3000),"0xe48947fb20dd411bd2853bd8adc1729fa45e6f51",BigInteger.valueOf(1000),"231231");

   BigInteger gas =  web3j.ethEstimateGas(tx).send().getAmountUsed();

   int a = 10;
    }

}