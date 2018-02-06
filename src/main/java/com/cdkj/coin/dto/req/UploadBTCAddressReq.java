package com.cdkj.coin.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class UploadBTCAddressReq {

    @NotBlank
    // @Pattern(regexp = "^0x+",message = "需要以0x开头")
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
