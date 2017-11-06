/**
 * @Title SYSDictAOImpl.java 
 * @Package com.xnjr.moom.ao.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 下午5:19:00 
 * @version V1.0   
 */
package com.cdkj.coin.ao.impl;

import java.util.Date;
import java.util.List;

import com.cdkj.coin.common.StringUtil;
import com.cdkj.coin.dto.req.SysDictAddReq;
import com.cdkj.coin.dto.req.SysDictUpdateReq;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.coin.ao.ISYSDictAO;
import com.cdkj.coin.bo.ISYSDictBO;
import com.cdkj.coin.bo.base.Paginable;
import com.cdkj.coin.domain.SYSDict;
import com.cdkj.coin.dto.req.XN623900Req;
import com.cdkj.coin.enums.EDictType;

/** 
 * @author: haiqingzheng 
 * @since: 2016年4月17日 下午5:19:00 
 * @history:
 */
@Service
public class SYSDictAOImpl implements ISYSDictAO {
    @Autowired
    ISYSDictBO sysDictBO;

    @Override
    public Long addSecondDict(SysDictAddReq req) {
        String parentKey = req.getParentKey();
        String key = req.getDkey();

        //检查 parentKey 是否存在，parentKey + key 是否唯一
        sysDictBO.checkKeys(parentKey, key);
        SYSDict sysDict = new SYSDict();
        sysDict.setType(EDictType.SECOND.getCode());
        sysDict.setParentKey(parentKey);
        sysDict.setDkey(key);
        sysDict.setDvalue(req.getDvalue());
        sysDict.setUpdater(req.getUpdater());
        sysDict.setUpdateDatetime(new Date());
        sysDict.setRemark(req.getRemark());

        //
        return sysDictBO.saveSecondDict(sysDict);
    }

    @Override
    public void dropSYSDict(Long id) {
        sysDictBO.removeSYSDict(id);
    }

    @Override
    public void editSYSDict(Long id, String value, String updater, String remark) {
        sysDictBO.refreshSYSDict(id, value, updater, remark);
    }

    @Override
    public Paginable<SYSDict> querySYSDictPage(int start, int limit,
            SYSDict condition) {
        return sysDictBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<SYSDict> querySysDictList(SYSDict condition) {
        return sysDictBO.querySYSDictList(condition);
    }

    @Override
    public SYSDict getSYSDict(Long id) {
        return sysDictBO.getSYSDict(id);
    }

    @Override
    public void updateSYSDict(SysDictUpdateReq req) {

         this.sysDictBO.refreshSYSDict(NumberUtils.createLong(req.getId()) ,req.getDvalue(),req.getUpdater(),req.getRemark());
    }
}
