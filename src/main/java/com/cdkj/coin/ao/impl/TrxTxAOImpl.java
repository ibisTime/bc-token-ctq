package com.cdkj.coin.ao.impl;

import com.cdkj.coin.Tron.TrxExplorer;
import com.cdkj.coin.Tron.TrxTx;
import com.cdkj.coin.ao.ITrxTxAO;
import com.cdkj.coin.bo.*;
import com.cdkj.coin.common.AmountUtil;
import com.cdkj.coin.common.JsonUtil;
import com.cdkj.coin.common.PropertiesUtil;
import com.cdkj.coin.common.SysConstants;
import com.cdkj.coin.domain.SYSConfig;
import com.cdkj.coin.domain.TrxTransaction;
import com.cdkj.coin.enums.EPushStatus;
import com.cdkj.coin.enums.ETrxType;
import com.cdkj.coin.exception.BizException;
import com.cdkj.coin.exception.EBizErrorCode;
import com.cdkj.coin.http.PostSimulater;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


@Service
public class TrxTxAOImpl implements ITrxTxAO {

    static final Logger logger = LoggerFactory.getLogger(TrxTxAOImpl.class);

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private ITrxAddressBO trxAddressBO;

    @Autowired
    private ITrxTransactionBO trxTransactionBO;

    @Override
    public void doTrxTransactionSync() {
        // trx交易需要1分钟来确认，大约需要经过20个交易
        while (true) {
            Long blockNumber = sysConfigBO
                .getLongValue(SysConstants.CUR_TRX_BLOCK_NUMBER);

            // 如果区块高度未达到待扫描的区块
            Long lasterBlockNumber = TrxExplorer.getLastBlockHeight();
            if (lasterBlockNumber < blockNumber) {
                break;
            }

            // 判断是否有足够的区块确认
            BigInteger blockConfirm = sysConfigBO
                .getBigIntegerValue(SysConstants.BLOCK_CONFIRM_TRX);
            if (blockNumber == null
                    || (lasterBlockNumber - blockNumber) < blockConfirm
                        .longValue()) {
                // System.out.println("*********同步循环结束,区块号"
                // + (blockNumber - 1) + "为最后一个可信任区块*******");
                break;
            }

            // 获取当前区块所有hash的列表(只能获取基于OMNI协议的hash)

            // 最小充值金额
            BigDecimal orangeMinChangeMoney = sysConfigBO
                .getBigDecimalValue(SysConstants.MIN_TRX_RECHARGE_MONEY);
            BigDecimal minChangeMoney = AmountUtil.toTrx(orangeMinChangeMoney);
            TrxTx trxtx = TrxExplorer.getTransactionList(blockNumber.intValue());
            List<TrxTransaction> trxTransactionList = trxTransactionBO.convertTx(trxtx);
            List<TrxTransaction> insertTrxTransactionList = new ArrayList<>();
            // 遍历查询交易记录
            for (TrxTransaction trxTransaction : trxTransactionList) {
                // 判断是否是合约交易
                if (!ETrxType.TransferContract.getCode().equals(trxTransaction.getType())) {
                    continue;
                }

                long toAddressCount = trxAddressBO
                    .queryAddressCount(trxTransaction.getTo());
                // 不是关注的则跳过
                if (toAddressCount <= 0) {
                    continue;
                }
                // 查询数据库是否落地,已经落地就跳过
                long txCount = trxTransactionBO
                    .getTotalCountByHash(trxTransaction.getHash());
                if (txCount > 0) {
                    continue;
                }

                BigDecimal amount = AmountUtil
                    .toTrx(trxTransaction.getAmount());
                // 等于0也直接跳过
                if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                    continue;
                }
                // 小于最小充值金额直接跳过
                if (minChangeMoney.compareTo(amount) > 0) {
                    continue;
                }
                trxTransaction.setBlockHeight(blockNumber.intValue());
                trxTransaction.setFee(TrxExplorer.getMineFee(trxTransaction.getHash()));
                insertTrxTransactionList.add(trxTransaction);
            }
            saveToDB(insertTrxTransactionList, blockNumber);
        }
    }

    private void saveToDB(List<TrxTransaction> trxTransactionList,
            Long blockNumber) {
        if (CollectionUtils.isNotEmpty(trxTransactionList)) {
            trxTransactionBO.addTrxTransactionList(trxTransactionList);

        }
        // 修改_区块遍历信息
        SYSConfig config = sysConfigBO
            .getSYSConfig(SysConstants.CUR_TRX_BLOCK_NUMBER);
        sysConfigBO.refreshSYSConfig(config.getId(),
            String.valueOf(blockNumber + 1), config.getUpdater(),
            config.getRemark());
    }

    // 时间调度任务,定期扫描——未推送的——交易
    public void pushTx() {
        TrxTransaction condition = new TrxTransaction();
        condition.setStatus(EPushStatus.UN_PUSH.getCode());
        List<TrxTransaction> txList = this.trxTransactionBO
            .getPaginable(0, 30, condition).getList();
        if (txList.size() > 0) {
            // 推送出去
            try {
                String pushJsonStr = JsonUtil.Object2Json(txList);
                String url = PropertiesUtil.Config.TRX_PUSH_ADDRESS_URL;
                Properties formProperties = new Properties();
                formProperties.put("trxTxlist", pushJsonStr);
                PostSimulater.requestPostForm(url, formProperties);
            } catch (Exception e) {
                logger.error("回调业务biz异常，原因：" + e.getMessage());
            }
        }
    }

    // 确认推送
    @Override
    public void confirmPush(List<Long> idList) {
        if (idList == null || idList.size() <= 0) {
            throw new BizException(
                EBizErrorCode.PUSH_STATUS_UPDATE_FAILURE.getErrorCode(),
                "请传入正确的json数组" + EBizErrorCode.PUSH_STATUS_UPDATE_FAILURE
                    .getErrorCode());
        }

        for (Long id : idList) {
            TrxTransaction data = trxTransactionBO.getTrxTransaction(id);
            trxTransactionBO.refreshStatus(data, EPushStatus.PUSHED.getCode());
        }
    }


}
