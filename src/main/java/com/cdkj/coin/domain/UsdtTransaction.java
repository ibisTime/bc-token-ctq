package com.cdkj.coin.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.cdkj.coin.dao.base.ABaseDO;

public class UsdtTransaction extends ABaseDO {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 1L;

    private BigInteger id;

    // 交易hash
    private String hash;

    // 发送地址
    private String from;

    // 收币地址
    private String to;

    // 数量
    private BigDecimal amount;

    // 手续费
    private BigDecimal fee;

    // 推送状态
    private String status;

    // 版本
    private int version;

    // 交易类型(数字)
    private int typeInt;

    // 交易类型(字符串)
    private String type;

    // 属性id
    private BigInteger propertyId;

    // 区块hash
    private String blockHash;

    //
    private int positioninblock;

    // 区块高度
    private int blockHeight;

    //
    private int confirmations;

    // 区块生成时间
    private Date blockCreatetime;

    public Date getBlockCreatetime() {
        return blockCreatetime;
    }

    public void setBlockCreatetime(Date blockCreatetime) {
        this.blockCreatetime = blockCreatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getTypeInt() {
        return typeInt;
    }

    public void setTypeInt(int typeInt) {
        this.typeInt = typeInt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigInteger getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(BigInteger propertyId) {
        this.propertyId = propertyId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public int getPositioninblock() {
        return positioninblock;
    }

    public void setPositioninblock(int positioninblock) {
        this.positioninblock = positioninblock;
    }

    public int getBlockHeight() {
        return blockHeight;
    }

    public void setBlockHeight(int blockHeight) {
        this.blockHeight = blockHeight;
    }

    public int getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(int confirmations) {
        this.confirmations = confirmations;
    }
}
