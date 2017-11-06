/**
 * @Title IEthAddressAO.java 
 * @Package com.cdkj.coin.ao 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年10月27日 下午5:38:33 
 * @version V1.0   
 */
package com.cdkj.coin.ao;

import com.cdkj.coin.bo.base.Paginable;
import com.cdkj.coin.domain.EthAddress;
import com.cdkj.coin.dto.req.UploadEthAddressReq;
import com.cdkj.coin.dto.res.UploadEthAddressRes;

import java.util.List;

/**
 * @author: haiqingzheng 
 * @since: 2017年10月27日 下午5:38:33 
 * @history:
 */
public interface IEthAddressAO {
    // 定时器调用：每隔**同步账户流水
    public void doEthTransactionSync();

    public UploadEthAddressRes uploadAddress(UploadEthAddressReq req);

    //
    public List<EthAddress> queryEthAddressListByAddress(String address);

    //根据状态列表 分页查
    public Paginable<EthAddress> queryEthAddressPageByStatusList(List<String> typeList, int start, int limit);


}
