/**
 * @Title BlockchainOutput.java 
 * @Package com.cdkj.coin.bitcoin 
 * @Description 
 * @author haiqingzheng  
 * @date Nov 14, 2018 2:33:12 PM 
 * @version V1.0   
 */
package com.cdkj.coin.bitcoin;

import java.math.BigInteger;

/** 
 * @author: haiqingzheng 
 * @since: Nov 14, 2018 2:33:12 PM 
 * @history:
 */
public class BlockchainOutput {

    private boolean spent;

    private BigInteger tx_index;

    private Integer type;

    private String addr;

    private BigInteger value;

    private Integer n;

    private String script;

    public boolean isSpent() {
        return spent;
    }

    public void setSpent(boolean spent) {
        this.spent = spent;
    }

    public BigInteger getTx_index() {
        return tx_index;
    }

    public void setTx_index(BigInteger tx_index) {
        this.tx_index = tx_index;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public BigInteger getValue() {
        return value;
    }

    public void setValue(BigInteger value) {
        this.value = value;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

}
