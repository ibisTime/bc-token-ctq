/**
 * @Title EthTransactionAOImpl.java 
 * @Package com.cdkj.coin.ao.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2018年6月11日 下午6:48:48 
 * @version V1.0   
 */
package com.cdkj.coin.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.coin.ao.IEthTransactionAO;
import com.cdkj.coin.bo.IEthTransactionBO;
import com.cdkj.coin.bo.base.Paginable;
import com.cdkj.coin.domain.EthTransaction;

/** 
 * @author: haiqingzheng 
 * @since: 2018年6月11日 下午6:48:48 
 * @history:
 */
@Service
public class EthTransactionAOImpl implements IEthTransactionAO {

    @Autowired
    private IEthTransactionBO ethTransactionBO;

    /** 
     * @see com.cdkj.coin.ao.IEthTransactionAO#queryEthTransactionPage(int, int, com.cdkj.coin.domain.EthTransaction)
     */
    @Override
    public Paginable<EthTransaction> queryEthTransactionPage(int start,
            int limit, EthTransaction condition) {
        return ethTransactionBO.getPaginable(start, limit, condition);
    }

}
