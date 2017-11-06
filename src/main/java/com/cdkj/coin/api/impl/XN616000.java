package com.cdkj.coin.api.impl;

import com.cdkj.coin.ao.IEthAddressAO;
import com.cdkj.coin.api.AProcessor;
import com.cdkj.coin.core.ObjValidater;
import com.cdkj.coin.dto.req.UploadEthAddressReq;
import com.cdkj.coin.exception.BizException;
import com.cdkj.coin.exception.ParaException;
import com.cdkj.coin.proxy.JsonUtil;
import com.cdkj.coin.spring.SpringContextHolder;

/**
 * Created by tianlei on 2017/十一月/01.
 */
public class XN616000 extends AProcessor {

    private IEthAddressAO addressAO = SpringContextHolder
            .getBean(IEthAddressAO.class);

    private UploadEthAddressReq req;

    @Override
    public Object doBusiness() throws BizException {

       return this.addressAO.uploadAddress(req);

    }

    @Override
    public void doCheck(String inputparams) throws ParaException {

        req = JsonUtil.json2Bean(inputparams,UploadEthAddressReq.class);
        ObjValidater.validateReq(req);

    }

}
