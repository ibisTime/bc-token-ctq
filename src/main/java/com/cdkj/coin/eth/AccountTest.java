/**
 * @Title AccountTest.java 
 * @Package ethereum 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年10月18日 下午7:49:59 
 * @version V1.0   
 */
package com.cdkj.coin.eth;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthBlock.TransactionResult;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.parity.Parity;

/** 
 * @author: haiqingzheng 
 * @since: 2017年10月18日 下午7:49:59 
 * @history:
 */
public class AccountTest {

    public static void main(String args[]) {
        Parity parity = Parity
            .build(new HttpService("http://116.62.6.195:8545"));
        try {
            EthBlock ethBlockResp = parity.ethGetBlockByNumber(
                new DefaultBlockParameterNumber(44334242423223L), true).send();
            List<TransactionResult> aa = ethBlockResp.getResult()
                .getTransactions();
            for (TransactionResult<Transaction> transactionResult : aa) {
                Transaction transaction = transactionResult.get();
                System.out.println(transaction.getTo());
                if (transaction.getTo().equalsIgnoreCase(
                    "0x901536393DF4bF66986C12cf98f3D6718C534F20")) {
                    System.out.println(transaction + "&*&*&*");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getBalance() {
        EthAccount account = new EthAccount();
        BigInteger ba = account
            .getBalance("0xb1cd852c72141bdac6ccf314d9ea82d2af03f2ac");
        System.out.print(ba);
    }

    public static void queryAccount() {
        EthAccount account = new EthAccount();
        List<String> accounts = account.getAccountlist();
        for (String accountId : accounts) {
            System.out.println(accountId);
        }

    }

    public static void createAccount() {
        EthAccount account = new EthAccount();
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setPhone("229787499");
        accountInfo.setAddress("世宁大厦");
        accountInfo.setSchool("buaa");
        accountInfo.setUserName("lzh");
        String accountId = account.createAccount("lzh", "123456", accountInfo);
        System.out.println("注册账户成功:" + accountId);
        // PersonalAccountsInfo.AccountsInfo accountsInfo =
        // account.getAccountInfo("0xad7bbca86e02e503076b06931e05938e51e49fb9");
        // System.out.println(accountsInfo.toString());
    }
}
