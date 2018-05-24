package com.cdkj.coin.api.impl;

import com.cdkj.coin.ao.IWanTxAO;
import com.cdkj.coin.api.AProcessor;
import com.cdkj.coin.core.ObjValidater;
import com.cdkj.coin.dto.req.WanPushTxConfirmReq;
import com.cdkj.coin.exception.BizException;
import com.cdkj.coin.exception.ParaException;
import com.cdkj.coin.proxy.JsonUtil;
import com.cdkj.coin.spring.SpringContextHolder;

/**
 * 万维链交易推送确认
 * @author: haiqingzheng 
 * @since: 2018年5月24日 下午8:53:41 
 * @history:
 */
public class XN626200 extends AProcessor {

    private IWanTxAO wanTxAO = SpringContextHolder.getBean(IWanTxAO.class);

    private WanPushTxConfirmReq req;

    @Override
    public Object doBusiness() throws BizException {

        return this.wanTxAO.confirmPush(req.getHashList());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {

        req = JsonUtil.json2Bean(inputparams, WanPushTxConfirmReq.class);
        ObjValidater.validateReq(req);

    }
}
