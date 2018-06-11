/**
 * @Title IEthTransactionAO.java 
 * @Package com.cdkj.coin.ao 
 * @Description 
 * @author haiqingzheng  
 * @date 2018年6月11日 下午6:47:32 
 * @version V1.0   
 */
package com.cdkj.coin.ao;

import com.cdkj.coin.bo.base.Paginable;
import com.cdkj.coin.domain.EthTransaction;

/** 
 * @author: haiqingzheng 
 * @since: 2018年6月11日 下午6:47:32 
 * @history:
 */
public interface IEthTransactionAO {

    // 分页查询广播记录
    public Paginable<EthTransaction> queryEthTransactionPage(int start,
            int limit, EthTransaction condition);

}
