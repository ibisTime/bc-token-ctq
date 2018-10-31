package com.cdkj.coin.domain;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.cdkj.coin.dao.base.ABaseDO;

public class WanTokenEvent extends ABaseDO {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 1L;

    // id
    private BigInteger id;

    // 交易哈希
    private String hash;

    // token币发起地址
    private String tokenFrom;

    // token币接收地址
    private String tokenTo;

    // token币数量
    private BigDecimal tokenValue;

    private BigInteger tokenLogIndex;

    // 币种符号
    private String symbol;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getTokenFrom() {
        return tokenFrom;
    }

    public void setTokenFrom(String tokenFrom) {
        this.tokenFrom = tokenFrom;
    }

    public String getTokenTo() {
        return tokenTo;
    }

    public void setTokenTo(String tokenTo) {
        this.tokenTo = tokenTo;
    }

    public BigDecimal getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(BigDecimal tokenValue) {
        this.tokenValue = tokenValue;
    }

    public BigInteger getTokenLogIndex() {
        return tokenLogIndex;
    }

    public void setTokenLogIndex(BigInteger tokenLogIndex) {
        this.tokenLogIndex = tokenLogIndex;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}
