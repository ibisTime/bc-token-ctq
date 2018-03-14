/**
 * @Title TokenClient.java 
 * @Package com.cdkj.coin.wallet.contract 
 * @Description 
 * @author xieyj  
 * @date 2018年3月7日 下午8:54:24 
 * @version V1.0   
 */
package com.cdkj.coin.contract;

import java.math.BigInteger;
import java.util.List;

import org.apache.log4j.Logger;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import com.cdkj.coin.common.PropertiesUtil;
import com.cdkj.coin.contract.OrangeCoinToken.TransferEventResponse;
import com.cdkj.coin.ethereum.Web3JClient;
import com.cdkj.coin.exception.BizException;
import com.cdkj.coin.exception.EBizErrorCode;

/** 
 * @author: xieyj 
 * @since: 2018年3月7日 下午8:54:24 
 * @history:
 */
public class TokenClient {

    private static Logger logger = Logger.getLogger(TokenClient.class);

    private volatile static OrangeCoinToken orangeCoinToken;

    // 加载合约
    public static OrangeCoinToken loadHolderContract() {
        try {
            Credentials credentials = WalletUtils.loadCredentials(
                PropertiesUtil.Config.CONTRACT_HOLDER_PWD,
                PropertiesUtil.Config.KEY_STORE_PATH + "/"
                        + PropertiesUtil.Config.CONTRACT_HOLDER_KEYSTORE);
            BigInteger gasLimit = BigInteger.valueOf(210000);
            BigInteger gasPrice = Web3JClient.getClient().ethGasPrice().send()
                .getGasPrice();
            BigInteger txFee = gasLimit.multiply(gasPrice);
            logger.info("以太坊平均价格=" + gasPrice + "，预计矿工费=" + txFee);
            synchronized (TokenClient.class) {
                if (orangeCoinToken == null) {
                    orangeCoinToken = OrangeCoinToken.load(
                        PropertiesUtil.Config.CONTRACT_ADDRESS,
                        Web3JClient.getClient(), credentials, gasPrice,
                        gasLimit);
                }
            }
        } catch (Exception e) {
            throw new BizException(EBizErrorCode.DEFAULT.getErrorCode(),
                "智能合约初始化失败,原因是" + e.getMessage());
        }
        return orangeCoinToken;
    }

    public static void main(String[] args) {
    }

    public static List<TransferEventResponse> loadTransferEvents(
            TransactionReceipt transactionReceipt) {

        List<OrangeCoinToken.TransferEventResponse> transferEventList = null;

        OrangeCoinToken contract = loadHolderContract();
        try {
            transferEventList = contract.getTransferEvents(transactionReceipt);
        } catch (Exception e) {
            throw new BizException(EBizErrorCode.DEFAULT.getErrorCode(),
                "智能合约解析交易事件失败，原因：" + e.getMessage());
        }
        return transferEventList;
    }

}
