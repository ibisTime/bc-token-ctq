package com.cdkj.coin.ao.impl;

import com.cdkj.coin.ao.IEthTxAO;
import com.cdkj.coin.bo.IEthAddressBO;
import com.cdkj.coin.bo.IEthTransactionBO;
import com.cdkj.coin.bo.ISYSConfigBO;
import com.cdkj.coin.bo.base.Paginable;
import com.cdkj.coin.common.DateUtil;
import com.cdkj.coin.common.JsonUtil;
import com.cdkj.coin.common.PropertiesUtil;
import com.cdkj.coin.common.SysConstants;
import com.cdkj.coin.domain.EthTransaction;
import com.cdkj.coin.domain.SYSConfig;
import com.cdkj.coin.dto.req.EthTxPageReq;
import com.cdkj.coin.enums.EPushStatus;
import com.cdkj.coin.enums.ESystemCode;
import com.cdkj.coin.eth.Web3JClient;
import com.cdkj.coin.exception.BizErrorCode;
import com.cdkj.coin.exception.BizException;
import com.cdkj.coin.http.PostSimulater;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by tianlei on 2017/十一月/02.
 */
@Service
public class EthTxAOImpl implements IEthTxAO {

    static final org.slf4j.Logger logger = LoggerFactory
            .getLogger(EthAddressAOImpl.class);

    private static Web3j web3j = Web3JClient.getClient();

    @Autowired
    private IEthAddressBO ethAddressBO;

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

        //时间
        Date startDate = null;
        Date endDate = null;

        if (req.getBlockCreateDatetimeStart() != null) {
            startDate = DateUtil.strToDate(req.getBlockCreateDatetimeStart(), DateUtil.DATA_TIME_PATTERN_1);
        }

        if (req.getBlockCreateDatetimeEnd() != null) {
            endDate = DateUtil.strToDate(req.getBlockCreateDatetimeEnd(), DateUtil.DATA_TIME_PATTERN_1);
        }

        if (startDate != null && endDate != null) {
            if (startDate.compareTo(endDate) > 0) {
                throw new BizException(BizErrorCode.DEFAULT_ERROR_CODE.getErrorCode(), "开始时间需 <= 结束时间");
            }

        }

        condation.setBlockCreateDatetimeStart(startDate);
        condation.setBlockCreateDatetimeEnd(endDate);

        return this.ethTransactionBO.getPaginable(Integer.valueOf(req.getStart()), Integer.valueOf(req.getLimit()), condation);
    }

    @Override
    public void doEthTransactionSync() {

        boolean isDebug = true;
        //
        try {
            //
            while (true) {

                Long blockNumber = sysConfigBO
                        .getLongValue(SysConstants.CUR_BLOCK_NUMBER);
                if (isDebug == true) {

                    System.out.println("*********同步循环开始，扫描区块" + blockNumber
                            + "*******");

                }

                //
                EthBlock ethBlockResp = web3j.ethGetBlockByNumber(
                        new DefaultBlockParameterNumber(blockNumber), true).send();
                if (ethBlockResp.getError() != null) {
                    logger.error("扫描以太坊区块同步流水发送异常，原因："
                            + ethBlockResp.getError());
                }

                //
                EthBlock.Block block = ethBlockResp.getResult();

                // 如果取到区块信息
                List<EthTransaction> transactionList = new ArrayList<>();

                if (block == null) {

                    if (isDebug == true) {

                        System.out.println("*********同步循环结束,区块号"
                                + (blockNumber - 1) + "为最后一个区块*******");
                    }
                    break;
                }

                if (block.getTransactions().size() <= 0) {

                    this.saveToDB(transactionList, blockNumber);
                    continue;
                }

                for (EthBlock.TransactionResult txObj : block.getTransactions()) {

                    EthBlock.TransactionObject tx = (EthBlock.TransactionObject) txObj;
                    String toAddress = tx.getTo();
                    String fromAddress = tx.getFrom();

                    if (StringUtils.isBlank(toAddress) || StringUtils.isBlank(fromAddress)) {
                        continue;
                    }

                    // 查询改地址是否在我们系统中存在
                    // to 或者 from 为我们的地址就要进行同步
                    long toCount = ethAddressBO.addressCount(toAddress);
                    long fromCount = ethAddressBO.addressCount(fromAddress);

                    if (toCount > 0 || fromCount > 0) {
                        // 需要同步

                        //获取交易收据
                        Optional<TransactionReceipt> transactionReceipt = web3j.ethGetTransactionReceipt(tx.getHash()).send().getTransactionReceipt();

                        if (transactionReceipt.isPresent()) {

                            TransactionReceipt transactionReceipt1 = transactionReceipt.get();
                            BigInteger gasUsed = transactionReceipt1.getGasUsed();
                            transactionList.add(this.ethTransactionBO.convertTx(tx, gasUsed, block.getTimestamp()));

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
    public void saveToDB(List<EthTransaction> transactionList, Long blockNumber) {
        //
        if (transactionList.isEmpty() == false) {

            this.ethTransactionBO.insertTxList(transactionList);

        }

        //修改_区块遍历信息
        SYSConfig config = sysConfigBO.getSYSConfig(
                SysConstants.CUR_BLOCK_NUMBER);
        //
        sysConfigBO.refreshSYSConfig(config.getId(),
                String.valueOf(blockNumber + 1), "code", "下次从哪个区块开始扫描");

    }

    //时间调度任务,定期扫描——未推送的——交易
    public void pushTx() {

        EthTransaction con = new EthTransaction();
        con.setStatus(EPushStatus.UN_PUSH.getCode());
        List<EthTransaction> txList = this.ethTransactionBO.queryEthTxPage(con, 0, 30);
        if (txList.size() > 0) {
            //推送出去
            try {

                String pushJsonStr = JsonUtil.Object2Json(txList);
                String url = PropertiesUtil.Config.PUSH_ADDRESS_URL;
                Properties formProperties = new Properties();
                formProperties.put("ethTxlist", pushJsonStr);
                PostSimulater.requestPostForm(url,
                        formProperties);
            } catch (Exception e) {
                throw new BizException("xn000000", "回调业务biz异常");
            }
        }

    }


    //确认推送
    @Override
    public Object confirmPush(List<String> hashList) {

        if (hashList == null || hashList.size() <= 0) {
            throw new BizException(BizErrorCode.PUSH_STATUS_UPDATE_FAILURE.getErrorCode(), "请传入正确的json数组" + BizErrorCode.PUSH_STATUS_UPDATE_FAILURE.getErrorCode());
        }

        this.ethTransactionBO.changeTxStatusToPushed(hashList);
        return new Object();

    }

}
