package com.cdkj.coin.api.impl;

import com.cdkj.coin.ao.IBTCTxAO;
import com.cdkj.coin.api.AProcessor;
import com.cdkj.coin.core.ObjValidater;
import com.cdkj.coin.dto.req.XN626100Req;
import com.cdkj.coin.dto.res.BooleanRes;
import com.cdkj.coin.exception.BizException;
import com.cdkj.coin.exception.ParaException;
import com.cdkj.coin.proxy.JsonUtil;
import com.cdkj.coin.spring.SpringContextHolder;

public class XN626100 extends AProcessor {

    private IBTCTxAO btcTxAO = SpringContextHolder.getBean(IBTCTxAO.class);

    private XN626100Req req;

    /**
     * 统一业务处理
     *
     * @return
     * @throws BizException
     * @create: 2015-5-6 上午9:05:14 miyb
     * @history:
     */
    @Override
    public Object doBusiness() throws BizException {

        this.btcTxAO.confirmPush(req.getUtxoList());
        return new BooleanRes(true);

    }

    /**
     * 统一参数校验
     *
     * @param inputparams
     * @throws ParaException
     * @create: 2015-5-6 上午9:05:26 miyb
     * @history:
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {

        req = JsonUtil.json2Bean(inputparams, XN626100Req.class);
        ObjValidater.validateReq(req);

    }
}
