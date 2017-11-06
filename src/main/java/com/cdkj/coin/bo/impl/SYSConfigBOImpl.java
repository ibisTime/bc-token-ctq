package com.cdkj.coin.bo.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.coin.bo.ISYSConfigBO;
import com.cdkj.coin.bo.base.PaginableBOImpl;
import com.cdkj.coin.dao.ISYSConfigDAO;
import com.cdkj.coin.domain.SYSConfig;
import com.cdkj.coin.exception.BizException;

/**
 * 
 * @author: Gejin 
 * @since: 2016年4月17日 下午7:56:03 
 * @history:
 */

@Component
public class SYSConfigBOImpl extends PaginableBOImpl<SYSConfig> implements
        ISYSConfigBO {

    static Logger logger = Logger.getLogger(SYSConfigBOImpl.class);

    @Autowired
    private ISYSConfigDAO sysConfigDAO;

    @Override
    public int refreshSYSConfig(Long id, String cvalue, String updater,
            String remark) {
        SYSConfig data = new SYSConfig();
        data.setId(id);
        data.setCvalue(cvalue);
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        return sysConfigDAO.updateValue(data);
    }

    @Override
    public SYSConfig getSYSConfig(Long id) {
        SYSConfig sysConfig = null;
        if (id > 0) {
            SYSConfig condition = new SYSConfig();
            condition.setId(id);
            sysConfig = sysConfigDAO.select(condition);
        }
        if (sysConfig == null) {
            throw new BizException("xn000000", "id记录不存在");
        }
        return sysConfig;
    }

    @Override
    public SYSConfig getSYSConfig(String key) {

        SYSConfig sysConfig = null;
        if (StringUtils.isNotBlank(key) ) {
            SYSConfig condition = new SYSConfig();
            condition.setCkey(key);
            List<SYSConfig> sysConfigList = sysConfigDAO.selectList(condition);
            if (CollectionUtils.isNotEmpty(sysConfigList)) {
                sysConfig = sysConfigList.get(0);
            } else {
                throw new BizException("xn000000", key + "对应记录不存在");
            }
        }
        return sysConfig;
    }

    @Override
    public Double getDoubleValue(String key) {
        Double result = 0.0;
        SYSConfig config = getSYSConfig(key);
        try {
            result = Double.valueOf(config.getCvalue());
        } catch (Exception e) {
            logger.error("参数名为" + key + "的配置转换成Double类型发生错误, 原因："
                    + e.getMessage());
        }
        return result;
    }

    @Override
    public Integer getIntegerValue(String key) {
        Integer result = 0;
        SYSConfig config = getSYSConfig(key);
        try {
            result = Integer.valueOf(config.getCvalue());
        } catch (Exception e) {
            logger.error("参数名为" + key + "的配置转换成Integer类型发生错误, 原因："
                    + e.getMessage());
        }
        return result;
    }

    @Override
    public String getStringValue(String key) {
        SYSConfig config = getSYSConfig(key);
        return config.getCvalue();
    }

    @Override
    public Long getLongValue(String key) {
        Long result = 0L;
        SYSConfig config = getSYSConfig(key);
        try {
            result = Long.valueOf(config.getCvalue());
        } catch (Exception e) {
            logger.error("参数名为" + key + "的配置转换成Long类型发生错误, 原因："
                    + e.getMessage());
        }
        return result;
    }

}
