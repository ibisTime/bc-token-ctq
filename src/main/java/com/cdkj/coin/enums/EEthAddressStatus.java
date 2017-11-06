package com.cdkj.coin.enums;

public enum EEthAddressStatus {
    NORMAL("0", "正常"), INVALID("1", "弃用");

    EEthAddressStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
