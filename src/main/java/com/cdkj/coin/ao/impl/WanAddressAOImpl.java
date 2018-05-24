/**
 * @Title WanAddressAOImpl.java
 * @Package com.cdkj.coin.ao.impl
 * @Description
 * @author leo(haiqing)
 * @date 2017年10月27日 下午5:43:34
 * @version V1.0
 */
package com.cdkj.coin.ao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.coin.ao.IWanAddressAO;
import com.cdkj.coin.bo.IWanAddressBO;
import com.cdkj.coin.bo.base.Paginable;
import com.cdkj.coin.domain.WanAddress;
import com.cdkj.coin.dto.req.UploadWanAddressReq;
import com.cdkj.coin.exception.BizException;
import com.cdkj.coin.exception.EBizErrorCode;

/**
 * @author: haiqingzheng
 * @since: 2017年10月27日 下午5:43:34
 * @history:
 */
@Service
public class WanAddressAOImpl implements IWanAddressAO {
    static final Logger logger = LoggerFactory
        .getLogger(WanAddressAOImpl.class);

    @Autowired
    private IWanAddressBO wanAddressBO;

    @Override
    public void uploadAddress(UploadWanAddressReq req) {

        // 首先判断 地址 + 类型 是否已存在
        int alreadyCount = this.wanAddressBO
            .queryWanAddressCount(req.getAddress(), req.getType());

        // 已经存在 也告知成功告知地址添加成功
        if (alreadyCount <= 0) {
            this.wanAddressBO.uploadAddress(req);
        }

    }

    @Override
    public List<WanAddress> queryWanAddressListByAddress(String address) {

        WanAddress condition = new WanAddress();
        condition.setAddress(address);
        return this.wanAddressBO.queryWanAddressList(condition);
    }

    @Override
    public Paginable<WanAddress> queryWanAddressPageByStatusList(
            List<String> typeList, int start, int limit) {

        if (typeList != null && typeList.isEmpty()) {
            throw new BizException(EBizErrorCode.DEFAULT.getErrorCode(),
                "size 需大于 0");
        }
        //
        WanAddress condation = new WanAddress();
        condation.setTypeList(typeList);
        return this.wanAddressBO.getPaginable(start, limit, condation);

    }

}
