package com.cdkj.coin.enums;

public enum EUsdtEnv {
    TEST("test", "测试环境"), PROD("prod", "正式环境");

    EUsdtEnv(String code, String value) {
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
