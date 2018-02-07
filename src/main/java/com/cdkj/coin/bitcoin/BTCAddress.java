package com.cdkj.coin.bitcoin;

import java.util.Date;

import com.cdkj.coin.dao.base.ABaseDO;

public class BTCAddress extends ABaseDO {

    private static final long serialVersionUID = 1L;

    private String code;

    /* 地址 */
    private String address;

    private Date createDateTime;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

}
