package com.cdkj.coin.enums;

public enum ETrxType {

    TransferContract("TransferContract", "trx转账合约");

    ETrxType(String code, String value) {
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
