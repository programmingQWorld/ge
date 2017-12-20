package com.goodboy.picshop.dto;

/**
 * 封装返回的json数据
 * @param <T>
 */
public class JSONResult<T> {
    private boolean success;    // 是否成功标志
    private T data;             // 成功时返回的数据
    private String errorMsg;       // 错误信息

    public JSONResult() {
    }

    // 成功时的构造器
    public JSONResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    // 失败时的构造器
    public JSONResult(boolean success, String errorMsg) {
        this.success = success;
        this.errorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String error) {
        this.errorMsg = error;
    }
}
