package com.cdkj.coin.exception;

/**
 * Created by tianlei on 2017/十一月/02.
 */
public enum BizErrorCode {


    DEFAULT_ERROR_CODE("xn000000","自己填充"),
    PUSH_STATUS_UPDATE_FAILURE("eth000001","地址状态更新失败");

    private String errorCode;
    private String errorInfo;


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    BizErrorCode(String errorCode, String errorInfo) {
        this.errorCode = errorCode;
        this.errorInfo = errorInfo;
    }
}
