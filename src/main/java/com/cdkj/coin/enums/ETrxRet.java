package com.cdkj.coin.enums;

public enum ETrxRet {

    SUCCESS("SUCCESS", "成功");

    ETrxRet(String code, String value) {
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
