package com.cdkj.coin.domain;

import com.cdkj.coin.dao.base.ABaseDO;

/**
* eth交易
* @author: kongliang
* @since: 2018年09月10日 19:22:16
* @history:
*/
public class EthTransation extends ABaseDO {

	private static final long serialVersionUID = 1L;

	// ID
	private String id;

	// 交易哈希
	private String hash;

	// 交易次数
	private String nonce;

	// 区块号
	private String blockHash;

	// 区块号
	private String blockNumber;

	// 交易index
	private String blockCreateDatetime;

	// 转出地址
	private String transactionIndex;

	// 转入地址
	private String from;

	// 转入地址
	private String to;

	// 推送状态0:未推送 1:已推送
	private String value;

	// 同步时间
	private String syncDatetime;

	// gas价格
	private String gasPrice;

	// gas最大使用限制
	private String gasLimit;

	// gas实际使用量
	private String gasUsed;

	// gas手续费
	private String gasFee;

	// input 输入
	private String input;

	// publicKey
	private String publicKey;

	// raw
	private String raw;

	// r
	private String r;

	// w
	private String s;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getHash() {
		return hash;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getNonce() {
		return nonce;
	}

	public void setBlockHash(String blockHash) {
		this.blockHash = blockHash;
	}

	public String getBlockHash() {
		return blockHash;
	}

	public void setBlockNumber(String blockNumber) {
		this.blockNumber = blockNumber;
	}

	public String getBlockNumber() {
		return blockNumber;
	}

	public void setBlockCreateDatetime(String blockCreateDatetime) {
		this.blockCreateDatetime = blockCreateDatetime;
	}

	public String getBlockCreateDatetime() {
		return blockCreateDatetime;
	}

	public void setTransactionIndex(String transactionIndex) {
		this.transactionIndex = transactionIndex;
	}

	public String getTransactionIndex() {
		return transactionIndex;
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

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setSyncDatetime(String syncDatetime) {
		this.syncDatetime = syncDatetime;
	}

	public String getSyncDatetime() {
		return syncDatetime;
	}

	public void setGasPrice(String gasPrice) {
		this.gasPrice = gasPrice;
	}

	public String getGasPrice() {
		return gasPrice;
	}

	public void setGasLimit(String gasLimit) {
		this.gasLimit = gasLimit;
	}

	public String getGasLimit() {
		return gasLimit;
	}

	public void setGasUsed(String gasUsed) {
		this.gasUsed = gasUsed;
	}

	public String getGasUsed() {
		return gasUsed;
	}

	public void setGasFee(String gasFee) {
		this.gasFee = gasFee;
	}

	public String getGasFee() {
		return gasFee;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getInput() {
		return input;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setRaw(String raw) {
		this.raw = raw;
	}

	public String getRaw() {
		return raw;
	}

	public void setR(String r) {
		this.r = r;
	}

	public String getR() {
		return r;
	}

	public void setS(String s) {
		this.s = s;
	}

	public String getS() {
		return s;
	}

}