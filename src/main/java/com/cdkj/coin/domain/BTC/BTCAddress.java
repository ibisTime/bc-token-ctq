package com.cdkj.coin.domain.BTC;

import com.cdkj.coin.dao.base.ABaseDO;

import java.util.Date;

public class BTCAddress extends ABaseDO {


    private static final long serialVersionUID = 1L;

    private Integer id;

    /*地址*/
    private String address;

    /*地址类型*/
    private String type;

    private Date createDateTime;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}