package com.cdkj.coin.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.coin.bo.IUsdtTransactionBO;
import com.cdkj.coin.bo.base.PaginableBOImpl;
import com.cdkj.coin.common.DateUtil;
import com.cdkj.coin.dao.IUsdtTransactionDAO;
import com.cdkj.coin.domain.UsdtTransaction;
import com.cdkj.coin.enums.EPushStatus;
import com.cdkj.coin.omni.OmniTransaction;

@Component
public class UsdtTransactionBOImpl extends PaginableBOImpl<UsdtTransaction>
        implements IUsdtTransactionBO {

    @Autowired
    private IUsdtTransactionDAO usdtTransactionDAO;

    @Override
    public UsdtTransaction getTransactionByHash(String hash) {
        UsdtTransaction usdtTransaction = null;
        if (StringUtils.isNotBlank(hash)) {
            UsdtTransaction condition = new UsdtTransaction();
            condition.setHash(hash);
            usdtTransaction = usdtTransactionDAO.select(condition);
        }
        return usdtTransaction;
    }

    @Override
    public void savaTransaction(UsdtTransaction usdtTransaction) {
        if (null != usdtTransaction) {
            usdtTransactionDAO.insert(usdtTransaction);
        }
    }

    @Override
    public long getTotalCountByAddress(String address) {
        long count = 0;
        if (StringUtils.isNotBlank(address)) {
            UsdtTransaction condition = new UsdtTransaction();
            condition.setTo(address);
            count = usdtTransactionDAO.selectTotalCount(condition);
        }
        return count;
    }

    @Override
    public UsdtTransaction convertTx(OmniTransaction omniTransaction) {
        UsdtTransaction usdtTransaction = new UsdtTransaction();
        if (null != omniTransaction) {
            usdtTransaction.setHash(omniTransaction.getTxId());
            usdtTransaction.setFrom(omniTransaction.getSendingAddress());
            usdtTransaction.setTo(omniTransaction.getReferenceAddress());
            usdtTransaction.setAmount(omniTransaction.getAmount());
            usdtTransaction.setFee(omniTransaction.getFee());

            usdtTransaction.setStatus(EPushStatus.UN_PUSH.getCode());
            usdtTransaction.setVersion(omniTransaction.getVersion());
            usdtTransaction.setTypeInt(omniTransaction.getType_int());
            usdtTransaction.setType(omniTransaction.getType());
            usdtTransaction.setPropertyId(omniTransaction.getPropertyId());

            usdtTransaction.setBlockHash(omniTransaction.getBlockHash());

            usdtTransaction.setBlockHeight(omniTransaction.getBlock());
            usdtTransaction
                .setConfirmations(omniTransaction.getConfirmations());

            String tempstemp = omniTransaction.getBlockTime() + "";
            Date bolckCreatTime = DateUtil.TimeStamp2Date(tempstemp,
                DateUtil.DATA_TIME_PATTERN_1);
            usdtTransaction.setBlockCreatetime(bolckCreatTime);
        }
        return usdtTransaction;
    }

    @Override
    public void addUsdtTransactionList(List<UsdtTransaction> usdtTransactionList) {
        if (CollectionUtils.isNotEmpty(usdtTransactionList)) {
            usdtTransactionDAO.insertList(usdtTransactionList);
        }
    }
}
