/**
 * @Title EthAddressAOImpl.java
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

import com.cdkj.coin.ao.IEthAddressAO;
import com.cdkj.coin.bo.IEthAddressBO;
import com.cdkj.coin.bo.base.Paginable;
import com.cdkj.coin.domain.EthAddress;
import com.cdkj.coin.dto.req.UploadEthAddressReq;
import com.cdkj.coin.exception.EBizErrorCode;
import com.cdkj.coin.exception.BizException;

/**
 * @author: haiqingzheng
 * @since: 2017年10月27日 下午5:43:34
 * @history:
 */
@Service
public class EthAddressAOImpl implements IEthAddressAO {
    static final Logger logger = LoggerFactory
        .getLogger(EthAddressAOImpl.class);

    @Autowired
    private IEthAddressBO ethAddressBO;

    @Override
    public void uploadAddress(UploadEthAddressReq req) {

        // 首先判断 地址 + 类型 是否已存在
        int alreadyCount = this.ethAddressBO
            .queryEthAddressCount(req.getAddress(), req.getType());

        // 已经存在 也告知成功告知地址添加成功
        if (alreadyCount <= 0) {
            this.ethAddressBO.uploadAddress(req);
        }

    }

    @Override
    public List<EthAddress> queryEthAddressListByAddress(String address) {

        EthAddress condition = new EthAddress();
        condition.setAddress(address);
        return this.ethAddressBO.queryEthAddressList(condition);
    }

    @Override
    public Paginable<EthAddress> queryEthAddressPageByStatusList(
            List<String> typeList, int start, int limit) {

        if (typeList != null && typeList.isEmpty()) {
            throw new BizException(
                EBizErrorCode.DEFAULT.getErrorCode(), "size 需大于 0");
        }
        //
        EthAddress condation = new EthAddress();
        condation.setTypeList(typeList);
        return this.ethAddressBO.getPaginable(start, limit, condation);

    }

}
