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
import org.web3j.protocol.Web3j;

import com.cdkj.coin.ao.IEthAddressAO;
import com.cdkj.coin.bo.IEthAddressBO;
import com.cdkj.coin.bo.IEthTransactionBO;
import com.cdkj.coin.bo.ISYSConfigBO;
import com.cdkj.coin.bo.base.Paginable;
import com.cdkj.coin.domain.EthAddress;
import com.cdkj.coin.dto.req.UploadEthAddressReq;
import com.cdkj.coin.eth.Web3JClient;
import com.cdkj.coin.exception.BizErrorCode;
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

    private static Web3j web3j = Web3JClient.getClient();

    @Autowired
    private IEthAddressBO ethAddressBO;

    @Autowired
    private IEthTransactionBO ethTransactionBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Override
    public void uploadAddress(UploadEthAddressReq req) {

        // 首先判断 地址 + 类型 是否已存在
        int alreadyCount = this.ethAddressBO.queryEthAddressCount(
            req.getAddress(), req.getType());

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
                BizErrorCode.DEFAULT_ERROR_CODE.getErrorCode(), "size 需大于 0");
        }
        //
        EthAddress condation = new EthAddress();
        condation.setTypeList(typeList);
        return this.ethAddressBO.getPaginable(start, limit, condation);

    }

}
