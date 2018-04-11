package com.cdkj.coin.domain;

import java.util.Date;

import com.cdkj.coin.dao.base.ABaseDO;

/**
 * 以太坊token地址
 * @author: xieyj 
 * @since: 2018年4月11日 上午11:34:07 
 * @history:
 */
public class TokenAddress extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // ID主键
    private String code;

    // 以太坊地址
    private String address;

    // 合约地址
    private String contractAddress;

    // 生成时间
    private Date createDatetime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }
}
