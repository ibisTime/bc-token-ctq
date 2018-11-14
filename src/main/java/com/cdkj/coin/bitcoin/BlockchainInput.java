package com.cdkj.coin.bitcoin;

import java.math.BigInteger;

public class BlockchainInput {

    private BigInteger sequence;

    private String witness;

    private String script;

    private BlockchainOutput prev_out;

    public BigInteger getSequence() {
        return sequence;
    }

    public void setSequence(BigInteger sequence) {
        this.sequence = sequence;
    }

    public String getWitness() {
        return witness;
    }

    public void setWitness(String witness) {
        this.witness = witness;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public BlockchainOutput getPrev_out() {
        return prev_out;
    }

    public void setPrev_out(BlockchainOutput prev_out) {
        this.prev_out = prev_out;
    }

}
