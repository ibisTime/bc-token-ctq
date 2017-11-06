package com.cdkj.coin.domain;

import java.util.Date;
import java.util.List;

import com.cdkj.coin.dao.base.ABaseDO;

/**
* 以太坊地址
* @author: haiqingzheng
* @since: 2017年10月23日 21:53:23
* @history:
*/
public class EthAddress extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // ID主键
    private String code;

    // 地址类型
    private String type;

    // 以太坊地址
    private String address;

    //生成时间
    private Date createDatetime;

    //###########  以下为查询所填加的属性 #############
    private List<String> typeList;

    public List<String> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<String> typeList) {
        this.typeList = typeList;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }
}
