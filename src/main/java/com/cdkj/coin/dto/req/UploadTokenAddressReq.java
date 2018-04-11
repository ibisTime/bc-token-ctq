package com.cdkj.coin.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class UploadTokenAddressReq {

    @NotBlank
    // @Pattern(regexp = "^0x+",message = "需要以0x开头")
    private String address;

    @NotBlank
    private String contractAddress;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }
}
