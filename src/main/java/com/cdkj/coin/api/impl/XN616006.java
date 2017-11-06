package com.cdkj.coin.api.impl;

import com.cdkj.coin.ao.IEthAddressAO;
import com.cdkj.coin.api.AProcessor;
import com.cdkj.coin.common.JsonUtil;
import com.cdkj.coin.core.ObjValidater;
import com.cdkj.coin.dto.req.AddressDetailReq;
import com.cdkj.coin.exception.BizException;
import com.cdkj.coin.exception.ParaException;
import com.cdkj.coin.spring.SpringContextHolder;

/**
 * Created by tianlei on 2017/十一月/03.
 */
public class XN616006 extends AProcessor {

    private IEthAddressAO addressAO = SpringContextHolder
            .getBean(IEthAddressAO.class);
    private AddressDetailReq req;


    @Override
    public void doCheck(String inputparams) throws ParaException {

        req = JsonUtil.json2Bean(inputparams,AddressDetailReq.class);
        ObjValidater.validateReq(req);

    }

    @Override
    public Object doBusiness() throws BizException {

      return this.addressAO.queryEthAddressListByAddress(req.getAddress());

    }
}
