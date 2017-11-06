/**
 * @Title EthAddressAOImpl.java
 * @Package com.cdkj.coin.ao.impl
 * @Description
 * @author leo(haiqing)
 * @date 2017年10月27日 下午5:43:34
 * @version V1.0
 */
package com.cdkj.coin.ao.impl;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.cdkj.coin.bo.base.Paginable;
import com.cdkj.coin.common.JsonUtil;
import com.cdkj.coin.domain.EthTransaction;
import com.cdkj.coin.dto.req.UploadEthAddressReq;
import com.cdkj.coin.dto.res.UploadEthAddressRes;
import com.cdkj.coin.enums.*;
import com.cdkj.coin.exception.BizErrorCode;
import com.cdkj.coin.exception.BizException;
import com.cdkj.coin.http.BizConnecter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.response.EthBlock;

import com.cdkj.coin.ao.IEthAddressAO;
import com.cdkj.coin.bo.IEthAddressBO;
import com.cdkj.coin.bo.IEthTransactionBO;
import com.cdkj.coin.bo.ISYSConfigBO;
import com.cdkj.coin.common.SysConstants;
import com.cdkj.coin.domain.EthAddress;
import com.cdkj.coin.domain.SYSConfig;
import com.cdkj.coin.eth.Web3JClient;

/**
 * @author: haiqingzheng
 * @since: 2017年10月27日 下午5:43:34
 * @history:
 */
@Service
public class EthAddressAOImpl implements IEthAddressAO {
    static final Logger logger = LoggerFactory
            .getLogger(EthAddressAOImpl.class);

    private static Web3j web3j = Web3JClient.getClient();

    @Autowired
    private IEthAddressBO ethAddressBO;

    @Autowired
    private IEthTransactionBO ethTransactionBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Override
    public UploadEthAddressRes uploadAddress(UploadEthAddressReq req) {

        //首先判断 地址 + 类型 是否已存在
        EthAddress queryCountCondation = new EthAddress();
        queryCountCondation.setAddress(req.getAddress());
        queryCountCondation.setType(req.getType());
       int alreadyCount = this.ethAddressBO.queryEthAddressCount(queryCountCondation);
       if (alreadyCount > 0) {
           throw  new  BizException(BizErrorCode.DEFAULT_ERROR_CODE.getErrorCode(),"地址 + 类型，已经存在");
       }

        UploadEthAddressRes res = this.ethAddressBO.uploadAddress(req);
        return res;

    }

    @Override
    public List<EthAddress> queryEthAddressListByAddress(String address) {

        EthAddress condition = new EthAddress();
        condition.setAddress(address);
        return  this.ethAddressBO.queryEthAddressList(condition);
    }

    @Override
    public Paginable<EthAddress> queryEthAddressPageByStatusList(List<String> typeList, int start, int limit){

        if (typeList != null && typeList.isEmpty()) {
            throw  new BizException(BizErrorCode.DEFAULT_ERROR_CODE.getErrorCode(),"size 需大于 0");
        }
        //
        EthAddress condation = new EthAddress();
        condation.setTypeList(typeList);
        return this.ethAddressBO.getPaginable(start,limit,condation);

    }


    @Override
    public void doEthTransactionSync() {

        try {
            //
            while (true) {

                Long blockNumber = sysConfigBO
                        .getLongValue(SysConstants.CUR_BLOCK_NUMBER);

                System.out.println("*********同步循环开始，扫描区块" + blockNumber
                        + "*******");

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
/**/
                List<EthTransaction> transactionList = new ArrayList<>();

                if (block == null) {

                    System.out.println("*********同步循环结束,区块号"
                            + (blockNumber - 1) + "为最后一个区块*******");
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

                    if (StringUtils.isNotBlank(toAddress)) {


                        // 查询改地址是否在我们系统中存在
                        // to 或者 from 为我们的地址就要进行同步
                        long toCount = ethAddressBO.addressCount(toAddress);
                        long fromCount = ethAddressBO.addressCount(fromAddress);

                        if (toCount > 0 || fromCount > 0) {
                            // 需要同步
                            transactionList.add(this.ethTransactionBO.convertTx(tx, block.getTimestamp()));
                        }

                    }

                    // 存储
                    this.saveToDB(transactionList, blockNumber);

                }

            }

        } catch (IOException e1) {

            logger.error("扫描以太坊区块同步流水发送异常，原因：" + e1.getMessage());
        }
        //
    }

    @Transactional
    private void saveToDB(List<EthTransaction> transactionList, Long blockNumber) {

        //
        if (transactionList.isEmpty() == false) {

            //需要落地交易
            for (EthTransaction tx : transactionList) {
                int count = this.ethTransactionBO.saveEthTransaction(tx);
            }

        }

        //修改 区块遍历 信息
        SYSConfig config = sysConfigBO.getSYSConfig(
                SysConstants.CUR_BLOCK_NUMBER);
        //
        sysConfigBO.refreshSYSConfig(config.getId(),
                String.valueOf(blockNumber + 1), "code", "当前扫描至哪个区块");

    }

    //时间调度
    public void pushTx() {

        EthTransaction con = new EthTransaction();
        con.setStatus(EPushStatus.UN_PUSH.getCode());
        List<EthTransaction> txs = this.ethTransactionBO.queryEthTransactionList(con);
        if (txs.size() > 0) {

            //连接另外的 biz
//            BizConnecter.getBizData("002000", JsonUtil.object2Json(req),
//                    Object.class);
            //推送出去
        }

    }

}


