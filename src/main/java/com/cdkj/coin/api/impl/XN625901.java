package com.cdkj.coin.api.impl;

import com.cdkj.coin.ao.ISYSDictAO;
import com.cdkj.coin.api.AProcessor;
import com.cdkj.coin.common.JsonUtil;
import com.cdkj.coin.common.StringUtil;
import com.cdkj.coin.core.ObjValidater;
import com.cdkj.coin.dto.req.SysDictAddReq;
import com.cdkj.coin.dto.req.SysDictDeleteReq;
import com.cdkj.coin.exception.BizException;
import com.cdkj.coin.exception.ParaException;
import com.cdkj.coin.spring.SpringContextHolder;

/**
 * Created by tianlei on 2017/十一月/04.
 */
public class XN625901 extends AProcessor {


    private ISYSDictAO dictAO = SpringContextHolder
            .getBean(ISYSDictAO.class);

    // 删除 二级 数据字典
    SysDictDeleteReq req;

    @Override
    public Object doBusiness() throws BizException {

        this.dictAO.dropSYSDict( Long.valueOf(req.getId()));
        return new Object();
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {

        //
        req = JsonUtil.json2Bean(inputparams,SysDictDeleteReq.class);
        ObjValidater.validateReq(req);

    }
}
