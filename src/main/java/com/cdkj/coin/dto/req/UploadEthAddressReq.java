package com.cdkj.coin.dto.req;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * Created by tianlei on 2017/十一月/01.
 */
public class UploadEthAddressReq {

    @NotBlank
    @Length(min = 42,max = 42)
//    @Pattern(regexp = "^0x+",message = "需要以0x开头")
    private String address;

    //地址类型
    @NotBlank
    @Length(max = 4)
    private String type;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
