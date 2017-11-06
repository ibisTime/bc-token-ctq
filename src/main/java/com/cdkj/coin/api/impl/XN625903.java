package com.cdkj.coin.api.impl;

import com.cdkj.coin.ao.ISYSDictAO;
import com.cdkj.coin.api.AProcessor;
import com.cdkj.coin.common.JsonUtil;
import com.cdkj.coin.core.ObjValidater;
import com.cdkj.coin.domain.SYSDict;
import com.cdkj.coin.dto.req.SysDictListReq;
import com.cdkj.coin.dto.req.SysDictUpdateReq;
import com.cdkj.coin.exception.BizException;
import com.cdkj.coin.exception.ParaException;
import com.cdkj.coin.spring.SpringContextHolder;

/**
 * Created by tianlei on 2017/十一月/04.
 */
public class XN625903 extends AProcessor {

    private ISYSDictAO dictAO = SpringContextHolder
            .getBean(ISYSDictAO.class);

    // 列表查
    SysDictListReq req;

    @Override
    public Object doBusiness() throws BizException {

        //
        SYSDict sysDict = new SYSDict();
        sysDict.setParentKey(req.getParentKey());
        sysDict.setDkey(req.getKey());
        return this.dictAO.querySysDictList(sysDict);

    }

    @Override
    public void doCheck(String inputparams) throws ParaException {

        req = JsonUtil.json2Bean(inputparams,SysDictListReq.class);
        ObjValidater.validateReq(req);

    }
}
