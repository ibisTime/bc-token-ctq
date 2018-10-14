package com.cdkj.coin.omni;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OmniTransaction {

    // 交易hash
    private String txId;

    // 手续费
    private String fee;

    // 发送地址
    private String sendingAddress;

    // 接收地址
    private String referenceAddress;

    // 交易地址是否牵涉到钱包中的地址
    private boolean isMine;

    // 版本
    private int version;

    // 交易类型(数字)
    private int type_int;

    // 交易类型(字符串)
    private String type;

    // 属性id
    private BigInteger propertyId;

    // 是否是最小单位
    private boolean divisible;

    // 数量
    private BigDecimal amount;

    // 交易是否有效
    private boolean valid;

    // 区块hash
    private String blockHash;

    // 区块生成时间
    private int blockTime;

    //
    private int positioninblock;

    // 区块高度
    private int block;

    // 确认区块数量
    private int confirmations;

    public BigDecimal getFee() {
        return new BigDecimal(this.fee);
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean isMine) {
        this.isMine = isMine;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getType_int() {
        return type_int;
    }

    public void setType_int(int type_int) {
        this.type_int = type_int;
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

    public boolean isDivisible() {
        return divisible;
    }

    public void setDivisible(boolean divisible) {
        this.divisible = divisible;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public String getSendingAddress() {
        return sendingAddress;
    }

    public void setSendingAddress(String sendingAddress) {
        this.sendingAddress = sendingAddress;
    }

    public String getReferenceAddress() {
        return referenceAddress;
    }

    public void setReferenceAddress(String referenceAddress) {
        this.referenceAddress = referenceAddress;
    }

    public int getPositioninblock() {
        return positioninblock;
    }

    public void setPositioninblock(int positioninblock) {
        this.positioninblock = positioninblock;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(int confirmations) {
        this.confirmations = confirmations;
    }

    public int getBlockTime() {
        return blockTime;
    }

    public void setBlockTime(int blockTime) {
        this.blockTime = blockTime;
    }

}
