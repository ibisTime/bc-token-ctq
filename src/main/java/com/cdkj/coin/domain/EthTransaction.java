package com.cdkj.coin.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.cdkj.coin.dao.base.ABaseDO;

/**
* 以太坊交易
* @author: haiqingzheng
* @since: 2017年10月29日 19:13:13
* @history:
*/
public class EthTransaction extends ABaseDO {

    private static final long serialVersionUID = 1L;

    private Long id;

    // 交易hash
    private String hash;

    // 第几个交易
    private BigInteger nonce;

    // 区块hash
    private String blockHash;

    // transactionIndex
    private BigInteger transactionIndex;

    // 发送地址
    private String from;

    // 接受地址
    private String to;

    // 数量
    private BigDecimal value;

    // gas价格
    private BigDecimal gasPrice;

    // 区块生成时间
    private Date blockCreateDatetime;

    // 区块生成时间
    private Date syncDatetime;

    private String status;

    // 区块编号
    private BigInteger blockNumber;

    // gasLimit
    private BigInteger gasLimit;

    // 消耗gas
    private BigInteger gasUsed;

    // gas手续费
    private BigDecimal gasFee;

    // 输出input
    private String input;

    // public key
    private String publicKey;

    // raw
    private String raw;

    // r
    private String r;

    // s
    private String s;

    public BigInteger getGasUsed() {
        return gasUsed;
    }

    public void setGasUsed(BigInteger gasUsed) {
        this.gasUsed = gasUsed;
    }

    // ################# 一下属性为查询而添加 ##############
    private List<EthTokenEvent> tokenEventList;

    private Date blockCreateDatetimeStart;

    private Date blockCreateDatetimeEnd;

    public Date getBlockCreateDatetimeStart() {
        return blockCreateDatetimeStart;
    }

    public void setBlockCreateDatetimeStart(Date blockCreateDatetimeStart) {
        this.blockCreateDatetimeStart = blockCreateDatetimeStart;
    }

    public Date getBlockCreateDatetimeEnd() {
        return blockCreateDatetimeEnd;
    }

    public void setBlockCreateDatetimeEnd(Date blockCreateDatetimeEnd) {
        this.blockCreateDatetimeEnd = blockCreateDatetimeEnd;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Date getBlockCreateDatetime() {
        return blockCreateDatetime;
    }

    public void setBlockCreateDatetime(Date blockCreateDatetime) {
        this.blockCreateDatetime = blockCreateDatetime;
    }

    public Date getSyncDatetime() {
        return syncDatetime;
    }

    public void setSyncDatetime(Date syncDatetime) {
        this.syncDatetime = syncDatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getGasLimit() {
        return gasLimit;
    }

    public void setGasLimit(BigInteger gasLimit) {
        this.gasLimit = gasLimit;
    }

    public BigDecimal getGasFee() {
        return gasFee;
    }

    public void setGasFee(BigDecimal gasFee) {
        this.gasFee = gasFee;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getHash() {
        return hash;
    }

    public BigInteger getNonce() {
        return nonce;
    }

    public void setNonce(BigInteger nonce) {
        this.nonce = nonce;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockNumber(BigInteger blockNumber) {
        this.blockNumber = blockNumber;
    }

    public BigInteger getBlockNumber() {
        return blockNumber;
    }

    public BigInteger getTransactionIndex() {
        return transactionIndex;
    }

    public void setTransactionIndex(BigInteger transactionIndex) {
        this.transactionIndex = transactionIndex;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFrom() {
        return from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTo() {
        return to;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setGasPrice(BigDecimal gasPrice) {
        this.gasPrice = gasPrice;
    }

    public BigDecimal getGasPrice() {
        return gasPrice;
    }

    public List<EthTokenEvent> getTokenEventList() {
        return tokenEventList;
    }

    public void setTokenEventList(List<EthTokenEvent> tokenEventList) {
        this.tokenEventList = tokenEventList;
    }
}
