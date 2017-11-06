package com.cdkj.coin.dto.req;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

/**
 * Created by tianlei on 2017/十一月/03.
 */
public class EthTxPageReq {

    @NotBlank
    @DecimalMin(value = "0")
    private String start;

    @NotBlank
    @DecimalMin(value = "1")
    private String limit;

    @NotBlank
    private String status;

    private String blockCreateDatetimeStart;
    private String blockCreateDatetimeEnd;

    private String from;
    private String to;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBlockCreateDatetimeStart() {
        return blockCreateDatetimeStart;
    }

    public void setBlockCreateDatetimeStart(String blockCreateDatetimeStart) {
        this.blockCreateDatetimeStart = blockCreateDatetimeStart;
    }

    public String getBlockCreateDatetimeEnd() {
        return blockCreateDatetimeEnd;
    }

    public void setBlockCreateDatetimeEnd(String blockCreateDatetimeEnd) {
        this.blockCreateDatetimeEnd = blockCreateDatetimeEnd;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
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
}
