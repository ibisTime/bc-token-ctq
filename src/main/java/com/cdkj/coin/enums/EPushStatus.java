package com.cdkj.coin.enums;

/**
 * Created by tianlei on 2017/十一月/02.
 */
public enum EPushStatus {

    UN_PUSH("0", "未推送"),
    PUSHED("1", "已推送");

    EPushStatus(String code, String value) {
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
