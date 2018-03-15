/**
 * @Title XN626140.java 
 * @Package com.cdkj.coin.api.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2018年3月14日 下午3:22:30 
 * @version V1.0   
 */
package com.cdkj.coin.api.impl;

import com.cdkj.coin.ao.ITokenTxAO;
import com.cdkj.coin.api.AProcessor;
import com.cdkj.coin.core.ObjValidater;
import com.cdkj.coin.dto.req.XN626160Req;
import com.cdkj.coin.dto.res.BooleanRes;
import com.cdkj.coin.exception.BizException;
import com.cdkj.coin.exception.ParaException;
import com.cdkj.coin.proxy.JsonUtil;
import com.cdkj.coin.spring.SpringContextHolder;

/** 
 * @author: haiqingzheng 
 * @since: 2018年3月14日 下午3:22:30 
 * @history:
 */
public class XN626160 extends AProcessor {

    private ITokenTxAO tokenTxAO = SpringContextHolder
        .getBean(ITokenTxAO.class);

    private XN626160Req req;

    /** 
     * @see com.cdkj.coin.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        tokenTxAO.confirmPush(req.getIdList());
        return new BooleanRes(true);
    }

    /** 
     * @see com.cdkj.coin.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN626160Req.class);
        ObjValidater.validateReq(req);
    }

}
