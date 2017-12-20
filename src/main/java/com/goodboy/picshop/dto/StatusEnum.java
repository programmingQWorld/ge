package com.goodboy.picshop.dto;

/**
 * 使用枚举类表述常量数据字典
 */
public enum StatusEnum {
    FAILURE(0, "失败"),
    SUCCESS(1, "成功"),
    FILE_TOO_LARGE(1020, "文件过大"),
    NOT_ALLOW_FILE_TYPE(1010, "不被允许上传的文件类型"),
    REPEAT_USER(2001,"用户名已被注册"),
    USER_ERROR(2002,"用户名不存在或密码错误"),
    UNKNOWN_ERROR(9999, "未知错误"),
    NO_COMMODITY_FOUND(1404, "没有找到商品"),
    NO_TAG_FOUND(5404, "没有找到标签"),
    DEL_ORDER_FAILD(3000, "您没有创建这个订单"),
    NO_DEL_ORDER_PRI(3001, "删除订单失败，没有权限，请登录"),
    INS_ORDER_FAILD(3002, "生成订单订单失败，商品已被别人抢先下单了，如果那边1小时内没有完成付款，您还有机会"),
    PAY_FAILED(3003, "支付失败，订单不存在或您已支付"),
    PAY_SUCCESS(3004, "支付成功"),
    NONE_ORDERS(3005, "没有任何关于你的订单" ),
    ORDER_UNKNOW(3006, "未知的订单"),
    NOT_PAY_YET(3007, "用户未付款"),
    NONE_CART_ITEMS(3008, "购物车是空的"),
    ORDER_NOT_EXIST(3009, "订单不存在"),
    ORDER_PAID(3010, "您已经付过款了");


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
