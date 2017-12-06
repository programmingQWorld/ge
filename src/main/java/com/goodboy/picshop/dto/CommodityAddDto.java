package com.goodboy.picshop.dto;

/**
 * 封装增加商品执行后的结果
 */
public class CommodityAddDto {

    private int status;     // 标识
    private String info;    // 标识信息

    public CommodityAddDto() {
    }

    public CommodityAddDto(StatusEnum statusEnum) {
        this.status = statusEnum.getStatus();
        this.info = statusEnum.getInfo();
    }

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

    @Override
    public String toString() {
        return "CommodityAddDto{status = " + this.status + ", info = " + this.info + "}";
    }
}
