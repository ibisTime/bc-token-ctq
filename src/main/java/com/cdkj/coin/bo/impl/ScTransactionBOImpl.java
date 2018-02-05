package com.cdkj.coin.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.coin.bo.IScTransactionBO;
import com.cdkj.coin.bo.base.PaginableBOImpl;
import com.cdkj.coin.dao.IScTransactionDAO;
import com.cdkj.coin.domain.ScTransaction;
import com.cdkj.coin.exception.BizException;

@Component
public class ScTransactionBOImpl extends PaginableBOImpl<ScTransaction>
        implements IScTransactionBO {

    @Autowired
    private IScTransactionDAO scTransactionDAO;

    @Override
    public String saveScTransaction(ScTransaction data) {
        String code = null;
        if (data != null) {
            scTransactionDAO.insert(data);
        }
        return code;
    }

    @Override
    public List<ScTransaction> queryScTransactionList(ScTransaction condition) {
        return scTransactionDAO.selectList(condition);
    }

    @Override
    public ScTransaction getScTransaction(String transactionid) {
        ScTransaction data = null;
        if (StringUtils.isNotBlank(transactionid)) {
            ScTransaction condition = new ScTransaction();
            condition.setTransactionid(transactionid);
            data = scTransactionDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "SC交易记录不存在");
            }
        }
        return data;
    }

    // 批量改变 交易状态 为已推送
    @Override
    public void changeTxStatusToPushed(List<String> txHashList) {

        List<ScTransaction> txList = new ArrayList<ScTransaction>();

        for (String hash : txHashList) {
            ScTransaction scTransaction = new ScTransaction();
            scTransaction.setTransactionid(hash);
            txList.add(scTransaction);
        }

        this.scTransactionDAO.updateTxStatus(txList);

    }

    @Override
    public boolean isScTransactionExist(String transactionid) {
        boolean flag = false;
        if (StringUtils.isNotBlank(transactionid)) {
            ScTransaction condition = new ScTransaction();
            condition.setTransactionid(transactionid);
            if (scTransactionDAO.selectTotalCount(condition) > 0) {
                flag = true;
            }
        }
        return flag;
    }
}
