package com.cdkj.coin.api.impl;

import com.cdkj.coin.ao.IEthAddressAO;
import com.cdkj.coin.api.AProcessor;
import com.cdkj.coin.common.JsonUtil;
import com.cdkj.coin.core.ObjValidater;
import com.cdkj.coin.dto.req.AddressPageReq;
import com.cdkj.coin.exception.BizException;
import com.cdkj.coin.exception.ParaException;
import com.cdkj.coin.spring.SpringContextHolder;

/**
 * Created by tianlei on 2017/十一月/03.
 */
public class XN616005 extends AProcessor {

    private IEthAddressAO addressAO = SpringContextHolder
            .getBean(IEthAddressAO.class);

    AddressPageReq req;

    //分页查地址
    @Override
    public void doCheck(String inputparams) throws ParaException {

        req= JsonUtil.json2Bean(inputparams,AddressPageReq.class);
        ObjValidater.validateReq(req);

    }


    @Override
    public Object doBusiness() throws BizException {

     return  this.addressAO.queryEthAddressPageByStatusList(req.getTypeList(),Integer.valueOf(req.getStart()).intValue(),Integer.valueOf(req.getLimit()).intValue());

    }

}
