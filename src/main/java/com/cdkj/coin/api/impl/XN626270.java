/**
 * @Title XN626140.java 
 * @Package com.cdkj.coin.api.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2018年3月14日 下午3:22:30 
 * @version V1.0   
 */
package com.cdkj.coin.api.impl;

import com.cdkj.coin.ao.IUsdtTxAO;
import com.cdkj.coin.api.AProcessor;
import com.cdkj.coin.core.ObjValidater;
import com.cdkj.coin.dto.req.XN626270Req;
import com.cdkj.coin.dto.res.BooleanRes;
import com.cdkj.coin.exception.BizException;
import com.cdkj.coin.exception.ParaException;
import com.cdkj.coin.proxy.JsonUtil;
import com.cdkj.coin.spring.SpringContextHolder;

/**
 * 
 * @author: kongliang 
 * @since: 2018年10月8日 下午1:11:54 
 * @history:
 */
public class XN626270 extends AProcessor {

    private IUsdtTxAO usdtTxAO = SpringContextHolder.getBean(IUsdtTxAO.class);

    private XN626270Req req;

    /** 
     * @see com.cdkj.coin.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        usdtTxAO.confirmPush(req.getIdList());
        return new BooleanRes(true);
    }

    /** 
     * @see com.cdkj.coin.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN626270Req.class);
        ObjValidater.validateReq(req);
    }

}
