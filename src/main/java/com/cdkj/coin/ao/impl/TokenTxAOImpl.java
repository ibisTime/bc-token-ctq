package com.cdkj.coin.ao.impl;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import com.cdkj.coin.ao.ITokenTxAO;
import com.cdkj.coin.bo.IEthTransactionBO;
import com.cdkj.coin.bo.ISYSConfigBO;
import com.cdkj.coin.bo.ITokenAddressBO;
import com.cdkj.coin.bo.ITokenContractBO;
import com.cdkj.coin.bo.base.Paginable;
import com.cdkj.coin.common.DateUtil;
import com.cdkj.coin.common.JsonUtil;
import com.cdkj.coin.common.PropertiesUtil;
import com.cdkj.coin.common.SysConstants;
import com.cdkj.coin.contract.OrangeCoinToken.TransferEventResponse;
import com.cdkj.coin.contract.TokenClient;
import com.cdkj.coin.domain.EthTransaction;
import com.cdkj.coin.domain.SYSConfig;
import com.cdkj.coin.dto.req.EthTxPageReq;
import com.cdkj.coin.enums.EEthContractMethodID;
import com.cdkj.coin.enums.EPushStatus;
import com.cdkj.coin.ethereum.Web3JClient;
import com.cdkj.coin.exception.BizException;
import com.cdkj.coin.exception.EBizErrorCode;
import com.cdkj.coin.http.PostSimulater;

/**
 * Created by tianlei on 2017/十一月/02.
 */
@Service
public class TokenTxAOImpl implements ITokenTxAO {

    static final org.slf4j.Logger logger = LoggerFactory
        .getLogger(TokenTxAOImpl.class);

    private static Web3j web3j = Web3JClient.getClient();

    @Autowired
    private ITokenContractBO tokenContractBO;

    @Autowired
    private ITokenAddressBO tokenAddressBO;

    @Autowired
    private IEthTransactionBO ethTransactionBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Override
    public Paginable<EthTransaction> queryTxPage(EthTxPageReq req) {

        //
        EthTransaction condation = new EthTransaction();
        condation.setStatus(req.getStatus());
        condation.setTo(req.getTo());
        condation.setFrom(req.getFrom());

        // 时间
        Date startDate = null;
        Date endDate = null;

        if (req.getBlockCreateDatetimeStart() != null) {
            startDate = DateUtil.strToDate(req.getBlockCreateDatetimeStart(),
                DateUtil.DATA_TIME_PATTERN_1);
        }

        if (req.getBlockCreateDatetimeEnd() != null) {
            endDate = DateUtil.strToDate(req.getBlockCreateDatetimeEnd(),
                DateUtil.DATA_TIME_PATTERN_1);
        }

        if (startDate != null && endDate != null) {
            if (startDate.compareTo(endDate) > 0) {
                throw new BizException(EBizErrorCode.DEFAULT.getErrorCode(),
                    "开始时间需 <= 结束时间");
            }

        }

        condation.setBlockCreateDatetimeStart(startDate);
        condation.setBlockCreateDatetimeEnd(endDate);

        return this.ethTransactionBO.getPaginable(
            Integer.valueOf(req.getStart()), Integer.valueOf(req.getLimit()),
            condation);
    }

    @Override
    public void doTokenTransactionSync() {
        boolean isDebug = true;

        try {
            //
            while (true) {

                Long blockNumber = sysConfigBO
                    .getLongValue(SysConstants.CUR_TOKEN_BLOCK_NUMBER);

                if (isDebug == true) {
                    System.out.println(
                        "*********Token同步循环开始，扫描区块" + blockNumber + "*******");
                }

                // 获取当前扫描区块信息
                EthBlock.Block currentBlock = Web3JClient.getBlock(blockNumber);

                // 获取当前区块链长度
                BigInteger maxBlockNumber = Web3JClient.getCurBlockNumber();

                if (isDebug == true) {
                    System.out
                        .println("*********最大区块号" + maxBlockNumber + "*******");
                }

                // 判断是否有足够的区块确认 暂定12
                BigInteger blockConfirm = sysConfigBO
                    .getBigIntegerValue(SysConstants.BLOCK_CONFIRM_TOKEN);
                if (currentBlock == null || maxBlockNumber
                    .subtract(BigInteger.valueOf(blockNumber))
                    .compareTo(blockConfirm) < 0) {

                    if (isDebug == true) {
                        System.out.println("*********同步循环结束,区块号"
                                + (blockNumber - 1) + "为最后一个可信任区块*******");
                    }

                    break;
                }

                List<EthTransaction> transactionList = new ArrayList<>();

                // 如果取到区块信息
                for (EthBlock.TransactionResult txObj : currentBlock
                    .getTransactions()) {

                    EthBlock.TransactionObject tx = (EthBlock.TransactionObject) txObj;
                    String toAddress = tx.getTo();
                    String fromAddress = tx.getFrom();

                    if (StringUtils.isBlank(toAddress)
                            || StringUtils.isBlank(fromAddress)) {
                        continue;
                    }

                    // 如果to地址是橙提取关心合约地址，说明是执行合约的交易，进行解析
                    if (tokenContractBO.isTokenContractExist(toAddress)) {
                        // 1、获取该交易向下的event
                        // 2、遍历eventlist,检查event是否已经处理过，如果已经处理过，直接跳过
                        // 3、检查event中的 from to 是否是我们关心的地址
                        // 4、落地event

                        List<TransferEventResponse> transferEventList = TokenClient
                            .loadTransferEvents(tx.getHash());
                        if (CollectionUtils.isNotEmpty(transferEventList)) {
                            for (TransferEventResponse transferEventResponse : transferEventList) {

                            }
                        }
                    }

                    // 查询to地址是否是合约地址，并且Input中前缀是0xa9059cbb打头的转账方法,MethodID=0xa9059cbb
                    if (toAddress.equals(contractAddress.toLowerCase())
                            && tx.getInput().startsWith(
                                EEthContractMethodID.Transfer.getCode())) {
                        // 需要同步，判断是否已经处理过
                        if (ethTransactionBO
                            .isEthTransactionExist(tx.getHash())) {
                            continue;
                        }
                        // 获取交易收据
                        Optional<TransactionReceipt> transactionReceipt = web3j
                            .ethGetTransactionReceipt(tx.getHash()).send()
                            .getTransactionReceipt();

                        if (transactionReceipt.isPresent()) {

                            TransactionReceipt transactionReceipt1 = transactionReceipt
                                .get();
                            BigInteger gasUsed = transactionReceipt1
                                .getGasUsed();
                            EthTransaction ethTransaction = this.ethTransactionBO
                                .convertTx(tx, gasUsed,
                                    currentBlock.getTimestamp());
                            transactionList.add(ethTransaction);

                        }

                    }

                }
                // 存储
                this.saveToDB(transactionList, blockNumber);

            }

        } catch (IOException e1) {

            logger.error("扫描以太坊区块同步流水发送异常，原因：" + e1.getMessage());
        }

    }

    @Transactional
    public void saveToDB(List<EthTransaction> transactionList,
            Long blockNumber) {
        //
        if (transactionList.isEmpty() == false) {

            this.ethTransactionBO.insertTxList(transactionList);

        }

        // 修改_区块遍历信息
        SYSConfig config = sysConfigBO
            .getSYSConfig(SysConstants.CUR_ETH_BLOCK_NUMBER);
        //
        sysConfigBO.refreshSYSConfig(config.getId(),
            String.valueOf(blockNumber + 1), config.getUpdater(),
            config.getRemark());

    }

    // 时间调度任务,定期扫描——未推送的——交易
    public void pushTx() {

        EthTransaction con = new EthTransaction();
        con.setStatus(EPushStatus.UN_PUSH.getCode());
        List<EthTransaction> txList = this.ethTransactionBO.queryEthTxPage(con,
            0, 30);
        if (txList.size() > 0) {
            // 推送出去
            try {

                String pushJsonStr = JsonUtil.Object2Json(txList);
                String url = PropertiesUtil.Config.TOKEN_PUSH_ADDRESS_URL;
                Properties formProperties = new Properties();
                formProperties.put("ethTxlist", pushJsonStr);
                PostSimulater.requestPostForm(url, formProperties);
            } catch (Exception e) {
                logger.error("回调业务biz异常，原因：" + e.getMessage());
            }
        }

    }

    // 确认推送
    @Override
    public Object confirmPush(List<String> hashList) {

        if (hashList == null || hashList.size() <= 0) {
            throw new BizException(
                EBizErrorCode.PUSH_STATUS_UPDATE_FAILURE.getErrorCode(),
                "请传入正确的json数组" + EBizErrorCode.PUSH_STATUS_UPDATE_FAILURE
                    .getErrorCode());
        }

        this.ethTransactionBO.changeTxStatusToPushed(hashList);
        return new Object();

    }

}
