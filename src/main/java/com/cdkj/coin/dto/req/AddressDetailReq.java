package com.cdkj.coin.dto.req;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by tianlei on 2017/十一月/03.
 */
public class AddressDetailReq {

    @NotBlank
    @Length(min = 42,max = 42)
    private String address;

    //
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
