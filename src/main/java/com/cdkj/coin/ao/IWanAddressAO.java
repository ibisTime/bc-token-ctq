/**
 * @Title IWanAddressAO.java 
 * @Package com.cdkj.coin.ao 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年10月27日 下午5:38:33 
 * @version V1.0   
 */
package com.cdkj.coin.ao;

import java.util.List;

import com.cdkj.coin.bo.base.Paginable;
import com.cdkj.coin.domain.WanAddress;
import com.cdkj.coin.dto.req.UploadWanAddressReq;

/**
 * @author: haiqingzheng 
 * @since: 2017年10月27日 下午5:38:33 
 * @history:
 */
public interface IWanAddressAO {

    public void uploadAddress(UploadWanAddressReq req);

    //
    public List<WanAddress> queryWanAddressListByAddress(String address);

    // 根据类型 分页查
    public Paginable<WanAddress> queryWanAddressPageByStatusList(
            List<String> typeList, int start, int limit);

}
