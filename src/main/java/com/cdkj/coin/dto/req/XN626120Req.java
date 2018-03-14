/**
 * @Title XN626120Req.java 
 * @Package com.cdkj.coin.dto.req 
 * @Description 
 * @author haiqingzheng  
 * @date 2018年3月14日 下午3:04:29 
 * @version V1.0   
 */
package com.cdkj.coin.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: haiqingzheng 
 * @since: 2018年3月14日 下午3:04:29 
 * @history:
 */
public class XN626120Req {

    @NotBlank
    private String symbol;

    @NotBlank
    private String contractAddress;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

}
