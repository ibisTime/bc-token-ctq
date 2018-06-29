package com.cdkj.coin.domain;

import java.util.Date;

import com.cdkj.coin.dao.base.ABaseDO;

/**
* 基于万维链的token合约
* @author: haiqingzheng
* @since: 2018年03月14日 14:29:34
* @history:
*/
public class WTokenContract extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 币种符号
    private String symbol;

    // 合约地址
    private String contractAddress;

    // 创建时间
    private Date createDatetime;

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

}
