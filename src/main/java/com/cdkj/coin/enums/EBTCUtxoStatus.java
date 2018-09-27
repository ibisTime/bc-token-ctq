package com.cdkj.coin.enums;

public enum EBTCUtxoStatus {

    OUT_UN_PUSH("0", "输出未推送"), OUT_PUSHED("1", "输出已推送");

    EBTCUtxoStatus(String code, String value) {
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
