package com.cdkj.coin.api.impl;

import com.cdkj.coin.ao.IScTxAO;
import com.cdkj.coin.api.AProcessor;
import com.cdkj.coin.core.ObjValidater;
import com.cdkj.coin.dto.req.XN626060Req;
import com.cdkj.coin.dto.res.BooleanRes;
import com.cdkj.coin.exception.BizException;
import com.cdkj.coin.exception.ParaException;
import com.cdkj.coin.proxy.JsonUtil;
import com.cdkj.coin.spring.SpringContextHolder;

/**
 * 交易推送确认
 * @author: haiqingzheng 
 * @since: 2018年1月31日 下午2:48:01 
 * @history:
 */
public class XN626060 extends AProcessor {

    private IScTxAO scTxAO = SpringContextHolder.getBean(IScTxAO.class);

    private XN626060Req req;

    @Override
    public Object doBusiness() throws BizException {

        this.scTxAO.confirmPush(req.getHashList());

        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {

        req = JsonUtil.json2Bean(inputparams, XN626060Req.class);
        ObjValidater.validateReq(req);

    }
}
