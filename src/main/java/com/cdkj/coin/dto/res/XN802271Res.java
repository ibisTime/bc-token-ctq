/**
 * @Title XN802270Res.java 
 * @Package com.cdkj.coin.wallet.dto.res 
 * @Description 
 * @author haiqingzheng  
 * @date 2018年6月9日 下午8:34:35 
 * @version V1.0   
 */
package com.cdkj.coin.dto.res;

import java.math.BigDecimal;
import java.sql.Date;

/** 
 * @author: haiqingzheng 
 * @since: 2018年6月9日 下午8:34:35 
 * @history:
 */
public class XN802271Res {

    // 交易hash
    private String txHash;

    // 区块高度
    private String height;

    // 交易方向
    private String direction;

    // 来方地址
    private String from;

    // 去方地址
    private String to;

    // 交易发生金额
    private BigDecimal value;

    // 交易手续费
    private BigDecimal txFee;

    // 发生时间
    private Date transDatetime;

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getTxFee() {
        return txFee;
    }

    public void setTxFee(BigDecimal txFee) {
        this.txFee = txFee;
    }

    public Date getTransDatetime() {
        return transDatetime;
    }

    public void setTransDatetime(Date transDatetime) {
        this.transDatetime = transDatetime;
    }

}
