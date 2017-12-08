package com.goodboy.picshop.dto;

/**
 * 封装返回的json数据
 * @param <T>
 */
public class JSONResult<T> {
    private boolean success;    // 是否成功标志
    private T data;             // 成功时返回的数据
    private String error;       // 错误信息

    public JSONResult() {
    }

    // 成功时的构造器
    public JSONResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    // 失败时的构造器
    public JSONResult(boolean success, String error) {
        this.success = success;
        this.error = error;
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
