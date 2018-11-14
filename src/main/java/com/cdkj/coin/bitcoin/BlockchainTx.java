package com.cdkj.coin.bitcoin;

import java.math.BigInteger;
import java.util.List;

public class BlockchainTx {

    // 交易hash
    private String hash;

    // 区块打包时间
    private BigInteger time;

    // 输入个数
    private Integer vin_sz;

    // 输出个数
    private Integer vout_sz;

    // 输入列表
    List<BlockchainInput> inputs;

    // 输出列表
    List<BlockchainOutput> out;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public BigInteger getTime() {
        return time;
    }

    public void setTime(BigInteger time) {
        this.time = time;
    }

    public Integer getVin_sz() {
        return vin_sz;
    }

    public void setVin_sz(Integer vin_sz) {
        this.vin_sz = vin_sz;
    }

    public Integer getVout_sz() {
        return vout_sz;
    }

    public void setVout_sz(Integer vout_sz) {
        this.vout_sz = vout_sz;
    }

    public List<BlockchainInput> getInputs() {
        return inputs;
    }

    public void setInputs(List<BlockchainInput> inputs) {
        this.inputs = inputs;
    }

    public List<BlockchainOutput> getOut() {
        return out;
    }

    public void setOut(List<BlockchainOutput> out) {
        this.out = out;
    }

}
