package com.cdkj.coin.ao.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.coin.ao.IUsdtTxAO;
import com.cdkj.coin.ao.impl.BTCBlockDataService;
import com.cdkj.coin.bo.IBTCAddressBO;
import com.cdkj.coin.bo.ISYSConfigBO;
import com.cdkj.coin.bo.IUsdtTransactionBO;
import com.cdkj.coin.common.AmountUtil;
import com.cdkj.coin.common.JsonUtil;
import com.cdkj.coin.common.PropertiesUtil;
import com.cdkj.coin.common.SysConstants;
import com.cdkj.coin.domain.SYSConfig;
import com.cdkj.coin.domain.UsdtTransaction;
import com.cdkj.coin.enums.EPushStatus;
import com.cdkj.coin.enums.EUsdtEnv;
import com.cdkj.coin.exception.BizException;
import com.cdkj.coin.exception.EBizErrorCode;
import com.cdkj.coin.http.PostSimulater;
import com.cdkj.coin.omni.OmniTransaction;
import com.cdkj.coin.omni.UsdtClient;

@Service
public class UsdtTxAOImpl implements IUsdtTxAO {

    static final Logger logger = LoggerFactory.getLogger(UsdtTxAOImpl.class);

    @Autowired
    BTCBlockDataService blockDataService;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    IBTCAddressBO btcAddressBO;

    @Autowired
    private IUsdtTransactionBO usdtTransactionBO;

    @Override
    public void doUsdtTransactionSync() {
        logger.info("******usdt扫描区块开始******");
        // omni协议的propertyId，usdt为31，TOMNI为2，测试环境使用TOMNI代替USDT
        // 往"moneyqMan7uh8FqdCA2BV5yZ8qVrc9ikLP"地址转BTC可以获得TOMNI币，比例1BTC:100TOMNI
        BigInteger propertyID = PropertiesUtil.Config.USDT_ENV
            .equals(EUsdtEnv.PROD.getCode()) ? new BigInteger("31")
                : new BigInteger("2");
        while (true) {
            Long blockNumber = sysConfigBO
                .getLongValue(SysConstants.CUR_USDT_BLOCK_NUMBER);

            // 如果区块高度未达到待扫描的区块
            Long lasterBlockNumber = UsdtClient.getBlockHeight();
            if (lasterBlockNumber < blockNumber) {
                break;
            }

            // 判断是否有足够的区块确认
            BigInteger blockConfirm = sysConfigBO
                .getBigIntegerValue(SysConstants.BLOCK_CONFIRM_USDT);
            if (blockNumber == null
                    || (lasterBlockNumber - blockNumber) < blockConfirm
                        .longValue()) {
                // System.out.println("*********同步循环结束,区块号"
                // + (blockNumber - 1) + "为最后一个可信任区块*******");
                break;
            }

            // 获取当前区块所有hash的列表(只能获取基于OMNI协议的hash)
            List<String> hashList = UsdtClient
                .getOmniHashListByBlock(blockNumber.intValue());
            List<UsdtTransaction> usdtTransactionList = new ArrayList<UsdtTransaction>();
            // 最小充值金额
            BigDecimal minChangeMoney = sysConfigBO
                .getBigDecimalValue(SysConstants.MIN_USDT_RECHARGE_MONEY);
            // 遍历查询交易记录
            for (String hash : hashList) {
                OmniTransaction omniTransaction = UsdtClient
                    .getOmniTransInfoByTxid(hash);
                // 判断是否是usdt交易
                // PropertyId可能为Null,0代表BTC
                if (omniTransaction.getPropertyId() == null
                        || propertyID
                            .compareTo(omniTransaction.getPropertyId()) != 0) {
                    continue;
                }
                // 交易无效跳过
                if (!omniTransaction.isValid()) {
                    continue;
                }

                long toAddressCount = btcAddressBO
                    .queryAddressCount(omniTransaction.getReferenceAddress());
                // 不是关注的则跳过
                if (toAddressCount <= 0) {
                    continue;
                }
                // 查询数据库是否落地,已经落地就跳过
                long txCount = usdtTransactionBO
                    .getTotalCountByHash(omniTransaction.getTxId());
                if (txCount > 0) {
                    continue;
                }

                // if () {}

                // 小于最小充值金额直接跳过
                BigDecimal amount = AmountUtil.toUsdt(omniTransaction
                    .getAmount());
                if (minChangeMoney.compareTo(amount) > 0) {
                    continue;
                }

                UsdtTransaction ustdTransaction = usdtTransactionBO
                    .convertTx(omniTransaction);
                usdtTransactionList.add(ustdTransaction);
            }
            saveToDB(usdtTransactionList, blockNumber);
        }
    }

    private void saveToDB(List<UsdtTransaction> usdtTransactionList,
            Long blockNumber) {
        if (CollectionUtils.isNotEmpty(usdtTransactionList)) {
            usdtTransactionBO.addUsdtTransactionList(usdtTransactionList);

        }
        // 修改_区块遍历信息
        SYSConfig config = sysConfigBO
            .getSYSConfig(SysConstants.CUR_USDT_BLOCK_NUMBER);
        sysConfigBO.refreshSYSConfig(config.getId(),
            String.valueOf(blockNumber + 1), config.getUpdater(),
            config.getRemark());
    }

    // 时间调度任务,定期扫描——未推送的——交易
    public void pushTx() {
        UsdtTransaction condition = new UsdtTransaction();
        condition.setStatus(EPushStatus.UN_PUSH.getCode());
        List<UsdtTransaction> txList = this.usdtTransactionBO.getPaginable(0,
            30, condition).getList();
        if (txList.size() > 0) {
            // 推送出去
            try {

                String pushJsonStr = JsonUtil.Object2Json(txList);
                String url = PropertiesUtil.Config.USDT_PUSH_ADDRESS_URL;
                Properties formProperties = new Properties();
                formProperties.put("usdtTxlist", pushJsonStr);
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
                "请传入正确的json数组"
                        + EBizErrorCode.PUSH_STATUS_UPDATE_FAILURE
                            .getErrorCode());
        }

        for (Long id : idList) {
            UsdtTransaction data = usdtTransactionBO.getUsdtTransaction(id);
            usdtTransactionBO.refreshStatus(data, EPushStatus.PUSHED.getCode());
        }
    }

}
