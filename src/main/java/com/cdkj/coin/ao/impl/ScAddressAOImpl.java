/**
 * @Title ScAddressAOImpl.java
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
import org.web3j.protocol.Web3j;

import com.cdkj.coin.ao.IScAddressAO;
import com.cdkj.coin.bo.ISYSConfigBO;
import com.cdkj.coin.bo.IScAddressBO;
import com.cdkj.coin.bo.IScTransactionBO;
import com.cdkj.coin.bo.base.Paginable;
import com.cdkj.coin.domain.ScAddress;
import com.cdkj.coin.dto.req.UploadScAddressReq;
import com.cdkj.coin.ethereum.Web3JClient;
import com.cdkj.coin.exception.BizErrorCode;
import com.cdkj.coin.exception.BizException;

/**
 * @author: haiqingzheng
 * @since: 2017年10月27日 下午5:43:34
 * @history:
 */
@Service
public class ScAddressAOImpl implements IScAddressAO {
    static final Logger logger = LoggerFactory.getLogger(ScAddressAOImpl.class);

    private static Web3j web3j = Web3JClient.getClient();

    @Autowired
    private IScAddressBO scAddressBO;

    @Autowired
    private IScTransactionBO scTransactionBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Override
    public void uploadAddress(UploadScAddressReq req) {

        // 首先判断 地址 + 类型 是否已存在
        int alreadyCount = this.scAddressBO.queryScAddressCount(
            req.getAddress(), req.getType());

        // 已经存在 也告知成功告知地址添加成功
        if (alreadyCount <= 0) {
            this.scAddressBO.uploadAddress(req);
        }

    }

    @Override
    public List<ScAddress> queryScAddressListByAddress(String address) {

        ScAddress condition = new ScAddress();
        condition.setAddress(address);
        return this.scAddressBO.queryScAddressList(condition);
    }

    @Override
    public Paginable<ScAddress> queryScAddressPageByStatusList(
            List<String> typeList, int start, int limit) {

        if (typeList != null && typeList.isEmpty()) {
            throw new BizException(
                BizErrorCode.DEFAULT_ERROR_CODE.getErrorCode(), "size 需大于 0");
        }
        //
        ScAddress condation = new ScAddress();
        condation.setTypeList(typeList);
        return this.scAddressBO.getPaginable(start, limit, condation);

    }

}
