package com.cdkj.coin.api.impl;

import com.cdkj.coin.ao.IWanAddressAO;
import com.cdkj.coin.api.AProcessor;
import com.cdkj.coin.core.ObjValidater;
import com.cdkj.coin.dto.req.UploadWanAddressReq;
import com.cdkj.coin.dto.res.BooleanRes;
import com.cdkj.coin.exception.BizException;
import com.cdkj.coin.exception.ParaException;
import com.cdkj.coin.proxy.JsonUtil;
import com.cdkj.coin.spring.SpringContextHolder;

/**
 * 上传万维链地址
 * @author: haiqingzheng 
 * @since: 2018年5月24日 下午8:44:01 
 * @history:
 */
public class XN626180 extends AProcessor {

    private IWanAddressAO addressAO = SpringContextHolder
        .getBean(IWanAddressAO.class);

    private UploadWanAddressReq req;

    @Override
    public Object doBusiness() throws BizException {

        this.addressAO.uploadAddress(req);

        return new BooleanRes(true);

    }

    @Override
    public void doCheck(String inputparams) throws ParaException {

        req = JsonUtil.json2Bean(inputparams, UploadWanAddressReq.class);
        ObjValidater.validateReq(req);

    }

}
