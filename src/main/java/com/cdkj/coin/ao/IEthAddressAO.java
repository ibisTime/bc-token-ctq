/**
 * @Title IEthAddressAO.java 
 * @Package com.cdkj.coin.ao 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年10月27日 下午5:38:33 
 * @version V1.0   
 */
package com.cdkj.coin.ao;

import java.util.List;

import com.cdkj.coin.bo.base.Paginable;
import com.cdkj.coin.domain.EthAddress;
import com.cdkj.coin.dto.req.UploadEthAddressReq;

/**
 * @author: haiqingzheng 
 * @since: 2017年10月27日 下午5:38:33 
 * @history:
 */
public interface IEthAddressAO {

    public void uploadAddress(UploadEthAddressReq req);

    //
    public List<EthAddress> queryEthAddressListByAddress(String address);

    // 根据类型 分页查
    public Paginable<EthAddress> queryEthAddressPageByStatusList(
            List<String> typeList, int start, int limit);

}
