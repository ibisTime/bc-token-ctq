package com.cdkj.coin.enums;

public enum EEthContractMethodID {
    Transfer("0xa9059cbb", "转账");

    EEthContractMethodID(String code, String value) {
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

    public static void main(String[] args) {
        System.out
            .println("0xa9059cbb000000000000000000000000919ac3ff41ccb7a390ededc4150991c7ec4ad79a000000000000000000000000000000000000000000000000000000003b9aca00"
                .startsWith("0xa9059cbb"));
    }
}
