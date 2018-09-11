package com.cdkj.coin.enums;

public enum ETransactionRecetptStatus {
    FAILED("0x0", "失败"), SUCCESS("0x1", "成功");

    ETransactionRecetptStatus(String code, String value) {
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
