package com.cdkj.coin.api.impl;

import com.cdkj.coin.ao.IScAddressAO;
import com.cdkj.coin.api.AProcessor;
import com.cdkj.coin.core.ObjValidater;
import com.cdkj.coin.dto.req.UploadScAddressReq;
import com.cdkj.coin.dto.res.BooleanRes;
import com.cdkj.coin.exception.BizException;
import com.cdkj.coin.exception.ParaException;
import com.cdkj.coin.proxy.JsonUtil;
import com.cdkj.coin.spring.SpringContextHolder;

/**
 * @author: haiqingzheng 
 * @since: 2018年1月30日 下午7:54:48 
 * @history:
 */
public class XN626040 extends AProcessor {

    private IScAddressAO addressAO = SpringContextHolder
        .getBean(IScAddressAO.class);

    private UploadScAddressReq req;

    @Override
    public Object doBusiness() throws BizException {

        this.addressAO.uploadAddress(req);
        return new BooleanRes(true);

    }

    @Override
    public void doCheck(String inputparams) throws ParaException {

        req = JsonUtil.json2Bean(inputparams, UploadScAddressReq.class);
        ObjValidater.validateReq(req);

    }

}
