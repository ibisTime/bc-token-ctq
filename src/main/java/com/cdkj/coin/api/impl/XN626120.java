/**
 * @Title XN626120.java 
 * @Package com.cdkj.coin.api.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2018年3月14日 下午3:03:21 
 * @version V1.0   
 */
package com.cdkj.coin.api.impl;

import com.cdkj.coin.ao.ITokenContractAO;
import com.cdkj.coin.api.AProcessor;
import com.cdkj.coin.core.ObjValidater;
import com.cdkj.coin.dto.req.XN626120Req;
import com.cdkj.coin.dto.res.BooleanRes;
import com.cdkj.coin.exception.BizException;
import com.cdkj.coin.exception.ParaException;
import com.cdkj.coin.proxy.JsonUtil;
import com.cdkj.coin.spring.SpringContextHolder;

/** 
 * @author: haiqingzheng 
 * @since: 2018年3月14日 下午3:03:21 
 * @history:
 */
public class XN626120 extends AProcessor {

    private ITokenContractAO tokenContractAO = SpringContextHolder
        .getBean(ITokenContractAO.class);

    private XN626120Req req;

    /** 
     * @see com.cdkj.coin.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {

        synchronized (XN626120.class) {
            tokenContractAO.addTokenContract(req.getSymbol(),
                req.getContractAddress());
        }

        return new BooleanRes(true);
    }

    /** 
     * @see com.cdkj.coin.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN626120Req.class);
        ObjValidater.validateReq(req);
    }

}
