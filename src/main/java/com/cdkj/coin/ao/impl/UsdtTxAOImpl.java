package com.cdkj.coin.ao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.coin.ao.IUsdtTxAO;
import com.cdkj.coin.bo.IBTCAddressBO;
import com.cdkj.coin.bo.ISYSConfigBO;
import com.cdkj.coin.bo.IUsdtTransactionBO;
import com.cdkj.coin.common.SysConstants;
import com.cdkj.coin.domain.SYSConfig;
import com.cdkj.coin.domain.UsdtTransaction;
import com.cdkj.coin.omni.OmniTransaction;
import com.cdkj.coin.omni.UsdtClent;

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

    // omni的propertyId
    private static BigInteger propertyID = new BigInteger("2");

    @Override
    public void doUsdtTransactionSync() {
        logger.info("******usdt扫描区块开始******");
        while (true) {
            Long blockNumber = sysConfigBO
                .getLongValue(SysConstants.CUR_USDT_BLOCK_NUMBER);

            // 如果区块高度为达到带扫描的区块
            Long lasterBlockNumber = blockDataService.getBlockCount();
            if (lasterBlockNumber < blockNumber) {
                return;
            }

            // 获取当前区块所有hash的列表
            List<String> hashList = UsdtClent
                .getOmniHashListByBlock(blockNumber.intValue());
            List<UsdtTransaction> usdtTransactionList = new ArrayList<UsdtTransaction>();
            // 遍历查询交易记录
            for (String hash : hashList) {
                OmniTransaction omniTransaction = UsdtClent
                    .getOmniTransInfoByTxid(hash);
                // 判断是否是usdt交易
                // PropertyId可能为Null
                if (omniTransaction.getPropertyId() == null
                        || propertyID
                            .compareTo(omniTransaction.getPropertyId()) != 0) {
                    continue;
                }
                // 交易无效跳过
                if (!omniTransaction.isValid()) {
                    continue;
                }
                // 只落地我们关注的地址
                long toAddressCount = btcAddressBO
                    .queryAddressCount(omniTransaction.getReferenceAddress());
                // 不是关注的则跳过
                if (toAddressCount == 0) {
                    continue;
                }
                // 查询数据库是否落地,已经落地就跳过
                long txCount = usdtTransactionBO
                    .getTotalCountByAddress(omniTransaction
                        .getReferenceAddress());
                if (txCount > 0) {
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
}
