package com.cdkj.coin.api.impl;

import com.cdkj.coin.ao.ISYSDictAO;
import com.cdkj.coin.api.AProcessor;
import com.cdkj.coin.common.JsonUtil;
import com.cdkj.coin.core.ObjValidater;
import com.cdkj.coin.dto.req.SysDictUpdateReq;
import com.cdkj.coin.exception.BizException;
import com.cdkj.coin.exception.ParaException;
import com.cdkj.coin.spring.SpringContextHolder;

/**
 * Created by tianlei on 2017/十一月/04.
 */
public class XN625902 extends AProcessor {

    private ISYSDictAO iSYSDictAO = SpringContextHolder.getBean(ISYSDictAO.class);

//    @Autowired
//    private ISYSDictAO iSYSDictAO;

    //更新_请求
    private SysDictUpdateReq req;

    @Override
    public Object doBusiness() throws BizException {

        this.iSYSDictAO.updateSYSDict(req);
        return new Object();
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {

        req = JsonUtil.json2Bean(inputparams,SysDictUpdateReq.class);
        ObjValidater.validateReq(req);

    }
}
