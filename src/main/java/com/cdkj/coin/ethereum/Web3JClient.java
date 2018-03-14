/**
 * @Title Web3JClient.java
 * @Package ethereum
 * @Description
 * @author leo(haiqing)
 * @date 2017年10月18日 下午7:37:41
 * @version V1.0
 */
package com.cdkj.coin.ethereum;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.http.HttpService;

import com.cdkj.coin.common.PropertiesUtil;
import com.cdkj.coin.exception.BizException;

/**
 * @author: haiqingzheng
 * @since: 2017年10月18日 下午7:37:41
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
            throw new BizException("xn000000",
                "获取区块" + blockNumber + "发生异常，原因：" + e.getMessage());
        }
        return currentBlock;
    }

    public static BigInteger getCurBlockNumber() {
        BigInteger currentBlockNumber = null;
        try {

            currentBlockNumber = web3j.ethBlockNumber().send().getBlockNumber();

        } catch (Exception e) {
            throw new BizException("xn000000",
                "查询当前最大区块发生异常，原因：" + e.getMessage());
        }
        return currentBlockNumber;
    }

}
