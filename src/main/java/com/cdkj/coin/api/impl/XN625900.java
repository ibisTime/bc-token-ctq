package com.cdkj.coin.api.impl;

import com.cdkj.coin.ao.ISYSDictAO;
import com.cdkj.coin.api.AProcessor;
import com.cdkj.coin.common.JsonUtil;
import com.cdkj.coin.core.ObjValidater;
import com.cdkj.coin.dto.req.SysDictAddReq;
import com.cdkj.coin.exception.BizException;
import com.cdkj.coin.exception.ParaException;
import com.cdkj.coin.spring.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by tianlei on 2017/十一月/04.
 */
public class XN625900 extends AProcessor {

    private ISYSDictAO iSYSDictAO = SpringContextHolder
            .getBean(ISYSDictAO.class);

//    @Autowired
//    private ISYSDictAO iSYSDictAO;

    //增加 二级 数据字典
    SysDictAddReq req;

    @Override
    public Object doBusiness() throws BizException {

      return this.iSYSDictAO.addSecondDict(req);

    }

    @Override
    public void doCheck(String inputparams) throws ParaException {

        req = JsonUtil.json2Bean(inputparams,SysDictAddReq.class);
        ObjValidater.validateReq(req);

    }
}
