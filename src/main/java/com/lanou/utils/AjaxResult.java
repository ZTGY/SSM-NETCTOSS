package com.lanou.utils;

/**
 * Created by dllo on 17/10/16.
 */
public class AjaxResult {
    private Object message;
    private Integer errorCode;
    private Object data;

    public AjaxResult() {
    }

    public AjaxResult(Object data) {
        this.errorCode = 0;
        this.message = "成功";
        this.data = data;
    }

    public AjaxResult(Object message, Integer errorCode, Object data) {
        this.message = message;
        this.errorCode = errorCode;
        this.data = data;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AjaxResult{" +
                "message=" + message +
                ", errorCode=" + errorCode +
                ", data=" + data +
                '}';
    }
}
