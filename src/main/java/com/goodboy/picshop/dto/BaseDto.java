package com.goodboy.picshop.dto;

/**
 * 封装操作基类
 */
public class BaseDto {
    private int status;     //状态标识
    private String info;    //状态信息

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
