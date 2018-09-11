/**
 * @Title TokenClient.java 
 * @Package com.cdkj.coin.wallet.contract 
 * @Description 
 * @author xieyj  
 * @date 2018年3月7日 下午8:54:24 
 * @version V1.0   
 */
package com.cdkj.coin.ethereum;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ClientTransactionManager;

import com.cdkj.coin.common.PropertiesUtil;
import com.cdkj.coin.ethereum.OrangeCoinToken.TransferEventResponse;
import com.cdkj.coin.exception.BizException;
import com.cdkj.coin.exception.EBizErrorCode;

/** 
 * @author: xieyj 
 * @since: 2018年3月7日 下午8:54:24 
 * @history:
 */
public class Web3JClient {

    static final Logger logger = LoggerFactory.getLogger(Web3JClient.class);

    private static String ETH_URL = PropertiesUtil.Config.ETH_URL;

    private Web3JClient() {
    }

    private volatile static Web3j web3j;

    public static Web3j getClient() {
        if (web3j == null) {
            synchronized (Web3JClient.class) {
                if (web3j == null) {
                    web3j = Web3j.build(new HttpService(ETH_URL));
                }
            }
        }
        return web3j;
    }

    public static EthBlock.Block getBlock(Long blockNumber) {
        EthBlock.Block currentBlock = null;
        try {

            // 获取当前区块
            EthBlock ethBlockResp = getClient().ethGetBlockByNumber(
                new DefaultBlockParameterNumber(blockNumber), true).send();
            if (ethBlockResp.getError() != null) {
                logger.error("获取区块发送异常，原因：" + ethBlockResp.getError());
            }
            currentBlock = ethBlockResp.getResult();

        } catch (Exception e) {
            throw new BizException("xn000000", "获取区块" + blockNumber
                    + "发生异常，原因：" + e.getMessage());
        }
        return currentBlock;
    }

    public static BigInteger getCurBlockNumber() {
        BigInteger currentBlockNumber = null;
        try {

            currentBlockNumber = getClient().ethBlockNumber().send()
                .getBlockNumber();

        } catch (Exception e) {
            throw new BizException("xn000000", "查询当前最大区块发生异常，原因："
                    + e.getMessage());
        }
        return currentBlockNumber;
    }

    private volatile static OrangeCoinToken orangeCoinToken;

    // 加载合约
    public static OrangeCoinToken loadHolderContract(String contractAddress) {
        try {

            ClientTransactionManager transactionManager = new ClientTransactionManager(
                web3j, contractAddress);

            BigInteger gasLimit = BigInteger.valueOf(210000);
            BigInteger gasPrice = Web3JClient.getClient().ethGasPrice().send()
                .getGasPrice();
            synchronized (Web3JClient.class) {
                if (orangeCoinToken == null) {
                    orangeCoinToken = OrangeCoinToken.load(contractAddress,
                        getClient(), transactionManager, gasPrice, gasLimit);
                }
            }
        } catch (Exception e) {
            throw new BizException(EBizErrorCode.DEFAULT.getErrorCode(),
                "智能合约初始化失败,原因是" + e.getMessage());
        }
        return orangeCoinToken;
    }

    public static void main(String[] args) {

        try {
            TransactionReceipt transactionReceipt = getClient()
                .ethGetTransactionReceipt(
                    "0xb659b26edff576bf19e21154133b75653d47f26cd0d450b1fce9a4dab3a2a7cf")
                .send().getResult();
            List<TransferEventResponse> res = loadTransferEvents(transactionReceipt);
            System.out
                .println("0xb659b26edff576bf19e21154133b75653d47f26cd0d450b1fce9a4dab3a2a7cf");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static List<TransferEventResponse> loadTransferEvents(
            TransactionReceipt transactionReceipt) {

        List<OrangeCoinToken.TransferEventResponse> transferEventList = null;

        OrangeCoinToken contract = loadHolderContract(transactionReceipt
            .getTo());
        try {
            transferEventList = contract.getTransferEvents(transactionReceipt);
        } catch (Exception e) {
            throw new BizException(EBizErrorCode.DEFAULT.getErrorCode(),
                "智能合约解析交易事件失败，原因：" + e.getMessage());
        }
        return transferEventList;

    }

}
