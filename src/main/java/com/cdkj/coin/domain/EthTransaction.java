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

    // id
    private BigInteger id;

    // 交易hash
    private String hash;

    // 第几个交易
    private BigInteger nonce;

    // 区块hash
    private String blockHash;

    // 区块编号
    private BigInteger blockNumber;

    // 区块生成时间
    private Date blockCreateDatetime;

    // 交易index
    private BigInteger transactionIndex;

    // 发送地址
    private String from;

    // 接受地址
    private String to;

    // 数量
    private BigDecimal value;

    // 推送状态0未推送1已推送
    private String status;

    // 同步时间
    private Date syncDatetime;

    // gas价格
    private BigDecimal gasPrice;

    // gasLimit
    private BigInteger gasLimit;

    // 消耗gas
    private BigInteger gasUsed;

    // gas手续费
    private BigDecimal gasFee;

    // input 输入
    private String input;

    // 公钥
    private String publicKey;

    private String raw;

    private String r;

    private String s;

    public BigInteger getGasUsed() {
        return gasUsed;
    }

    public void setGasUsed(BigInteger gasUsed) {
        this.gasUsed = gasUsed;
    }

    // ################# 一下属性为查询而添加 ##############

    private List<TokenEvent> tokenEventList;

    private Date blockCreateDatetimeStart;

    private Date blockCreateDatetimeEnd;

    public List<TokenEvent> getTokenEventList() {
        return tokenEventList;
    }

    public void setTokenEventList(List<TokenEvent> tokenEventList) {
        this.tokenEventList = tokenEventList;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getGasLimit() {
        return gasLimit;
    }

    public void setGasLimit(BigInteger gasLimit) {
        this.gasLimit = gasLimit;
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

    public BigInteger getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(BigInteger blockNumber) {
        this.blockNumber = blockNumber;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getGasPrice() {
        return gasPrice;
    }

    public void setGasPrice(BigDecimal gasPrice) {
        this.gasPrice = gasPrice;
    }

    public BigDecimal getGasFee() {
        return gasFee;
    }

    public void setGasFee(BigDecimal gasFee) {
        this.gasFee = gasFee;
    }
}
