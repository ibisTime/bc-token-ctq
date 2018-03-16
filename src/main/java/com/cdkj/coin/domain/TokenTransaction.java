package com.cdkj.coin.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.cdkj.coin.dao.base.ABaseDO;

/**
* token交易
* @author: haiqingzheng
* @since: 2018年03月14日 21:05:46
* @history:
*/
public class TokenTransaction extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // ID
    private Long id;

    // 交易哈希
    private String hash;

    // 交易次数
    private BigInteger nonce;

    // 区块哈希
    private String blockHash;

    // 交易index
    private BigInteger transactionIndex;

    // 转出地址
    private String from;

    // 转入地址
    private String to;

    // 交易额
    private BigDecimal value;

    // input 输入
    private String input;

    // token币发起地址
    private String tokenFrom;

    // token币接收地址
    private String tokenTo;

    // token币数量
    private BigDecimal tokenValue;

    // event_log_index
    private BigInteger tokenLogIndex;

    // 区块形成时间
    private Date blockCreateDatetime;

    // 同步时间
    private Date syncDatetime;

    // 区块号
    private BigInteger blockNumber;

    // gas价格
    private BigDecimal gasPrice;

    // gas最大使用限制
    private BigInteger gasLimit;

    // gas实际使用量
    private BigInteger gasUsed;

    // 状态 0-未推送 1-已推送
    private String status;

    private String symbol;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public BigInteger getNonce() {
        return nonce;
    }

    public void setNonce(BigInteger nonce) {
        this.nonce = nonce;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public BigInteger getTransactionIndex() {
        return transactionIndex;
    }

    public void setTransactionIndex(BigInteger transactionIndex) {
        this.transactionIndex = transactionIndex;
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

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
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

    public BigInteger getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(BigInteger blockNumber) {
        this.blockNumber = blockNumber;
    }

    public BigDecimal getGasPrice() {
        return gasPrice;
    }

    public void setGasPrice(BigDecimal gasPrice) {
        this.gasPrice = gasPrice;
    }

    public BigInteger getGasLimit() {
        return gasLimit;
    }

    public void setGasLimit(BigInteger gasLimit) {
        this.gasLimit = gasLimit;
    }

    public BigInteger getGasUsed() {
        return gasUsed;
    }

    public void setGasUsed(BigInteger gasUsed) {
        this.gasUsed = gasUsed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}
