package com.goodboy.picshop.dto;

/**
 * 使用枚举类表述常量数据字典
 */
public enum StatusEnum {
    SUCCESS(1, "成功"),
    DEL_ORDER_FAILD(3000, "删除订单失败，您没有创建该订单"),
    NO_DEL_ORDER_PRI(3001, "删除订单失败，没有权限，请登录"),
    INS_ORDER_FAILD(3002, "生成订单订单失败，商品已被别人抢先下单了，如果那边1小时内没有完成付款，您还有机会");

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
