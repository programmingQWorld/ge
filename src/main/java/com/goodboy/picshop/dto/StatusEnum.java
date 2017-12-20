package com.goodboy.picshop.dto;

/**
 * 使用枚举类表述常量数据字典
 */
public enum StatusEnum {

    FAILURE(0, "失败"),
    SUCCESS(1, "成功"),
    FILE_TOO_LARGE(1020, "文件过大"),
    NOT_ALLOW_FILE_TYPE(1010, "不被允许上传的文件类型"),
    NO_LOGIN(2000,"用户未登录"),
    REPEAT_USER(2001,"用户名已被注册"),
    USER_ERROR(2002,"用户名不存在或密码错误"),
    NO_EMAILL(2003,"邮箱暂未被注册"),
    LINK_EXPIRED(2004,"链接已过期"),
    LINK_ERROR(2005,"链接错误"),
    UNKNOWN_ERROR(9999, "未知错误"),
    NO_COMMODITY_FOUND(1404, "没有找到商品"),
    NO_TAG_FOUND(5404, "没有找到标签");


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
