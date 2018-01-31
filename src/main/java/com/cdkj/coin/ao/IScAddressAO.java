/**
 * @Title IScAddressAO.java 
 * @Package com.cdkj.coin.ao 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年10月27日 下午5:38:33 
 * @version V1.0   
 */
package com.cdkj.coin.ao;

import java.util.List;

import com.cdkj.coin.bo.base.Paginable;
import com.cdkj.coin.domain.ScAddress;
import com.cdkj.coin.dto.req.UploadScAddressReq;

/**
 * @author: haiqingzheng 
 * @since: 2017年10月27日 下午5:38:33 
 * @history:
 */
public interface IScAddressAO {

    public void uploadAddress(UploadScAddressReq req);

    //
    public List<ScAddress> queryScAddressListByAddress(String address);

    // 根据类型 分页查
    public Paginable<ScAddress> queryScAddressPageByStatusList(
            List<String> typeList, int start, int limit);

}
