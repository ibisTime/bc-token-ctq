/**
 * @Title SYSDictBOImpl.java 
 * @Package com.xnjr.moom.bo.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 下午2:50:06 
 * @version V1.0   
 */
package com.cdkj.coin.bo.impl;

import java.util.Date;
import java.util.List;

import com.cdkj.coin.exception.BizErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.coin.bo.ISYSDictBO;
import com.cdkj.coin.bo.base.PaginableBOImpl;
import com.cdkj.coin.dao.ISYSDictDAO;
import com.cdkj.coin.domain.SYSDict;
import com.cdkj.coin.enums.EDictType;
import com.cdkj.coin.exception.BizException;

/** 
 * @author: haiqingzheng 
 * @since: 2016年4月17日 下午2:50:06 
 * @history:
 */
@Component
public class SYSDictBOImpl extends PaginableBOImpl<SYSDict> implements
        ISYSDictBO {
    @Autowired
    private ISYSDictDAO sysDictDAO;

    @Override
    public void removeSYSDict(Long id) {
        if (id > 0) {
            SYSDict data = new SYSDict();
            data.setId(id);
            sysDictDAO.delete(data);
        }
    }

    @Override
    public void refreshSYSDict(Long id, String value, String updater,
            String remark) {
        SYSDict data = new SYSDict();
        data.setId(id);
        data.setDvalue(value);
        data.setUpdater(updater);
        data.setRemark(remark);
        if (sysDictDAO.updateValue(data) <= 0) {
            throw new BizException(BizErrorCode.DEFAULT_ERROR_CODE.getErrorCode(),"更新失败");
        }

    }

    @Override
    public SYSDict getSYSDict(Long id) {
        SYSDict sysDict = null;
        if (id > 0) {
            SYSDict data = new SYSDict();
            data.setId(id);
            sysDict = sysDictDAO.select(data);
        }
        if (sysDict == null) {
            throw new BizException("xn000000", "id记录不存在");
        }
        return sysDict;
    }

    @Override
    public List<SYSDict> querySYSDictList(SYSDict condition) {
        return sysDictDAO.selectList(condition);
    }

    @Override
    public Long saveSecondDict(SYSDict sysDict) {
        Long id = null;
        if (sysDict != null) {
            sysDictDAO.insert(sysDict);
            id = sysDict.getId();
        }
        return id;
    }

    @Override
    public void checkKeys(String parentKey, String key) {
        // 查看父节点是否存在
        SYSDict fDict = new SYSDict();
        fDict.setDkey(parentKey);
        fDict.setType(EDictType.FIRST.getCode());

        if (getTotalCount(fDict) <= 0) {
            throw new BizException(BizErrorCode.DEFAULT_ERROR_CODE.getErrorCode(), "parentKey不存在");
        }
        // 第二层数据字典 在当前父节点下key不能重复
        SYSDict condition = new SYSDict();
        condition.setParentKey(parentKey);
        condition.setDkey(key);
        condition.setType(EDictType.SECOND.getCode());

        if (getTotalCount(condition) > 0) {
            throw new BizException(BizErrorCode.DEFAULT_ERROR_CODE.getErrorCode(), "当前节点下，key重复");
        }

    }

}
