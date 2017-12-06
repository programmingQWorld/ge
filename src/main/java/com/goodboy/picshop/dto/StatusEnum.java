package com.goodboy.picshop.dto;

/**
 * 使用枚举类表述常量数据字典
 */
public enum StatusEnum {
    SUCCESS(1, "成功");

    private int status;     //状态标识符
    private String info;    //状态标识信息

    private StatusEnum(int status, String info) {
        this.status = status;
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }
}
