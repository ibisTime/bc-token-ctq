/**
 * @Title BlockchainBlockInfo.java 
 * @Package com.cdkj.coin.bitcoin 
 * @Description 
 * @author haiqingzheng  
 * @date Nov 14, 2018 2:31:22 PM 
 * @version V1.0   
 */
package com.cdkj.coin.bitcoin;

import java.math.BigInteger;
import java.util.List;

/** 
 * @author: haiqingzheng 
 * @since: Nov 14, 2018 2:31:22 PM 
 * @history:
 */
public class BlockchainBlock {

    // 区块hash
    private String hash;

    // 区块打包时间
    private BigInteger time;

    // 区块内交易数量
    private BigInteger n_tx;

    // 区块高度
    private BigInteger height;

    // 交易列表
    List<BlockchainTx> tx;

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

    public BigInteger getN_tx() {
        return n_tx;
    }

    public void setN_tx(BigInteger n_tx) {
        this.n_tx = n_tx;
    }

    public BigInteger getHeight() {
        return height;
    }

    public void setHeight(BigInteger height) {
        this.height = height;
    }

    public List<BlockchainTx> getTx() {
        return tx;
    }

    public void setTx(List<BlockchainTx> tx) {
        this.tx = tx;
    }

}
