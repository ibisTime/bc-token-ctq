package com.cdkj.coin.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class UploadTrxAddressReq {

    @NotBlank
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
