package com.goodboy.picshop.dto;

import com.goodboy.picshop.entity.Commodity;

import java.util.List;

/**
 * 封装查询商品的执行结果
 */
public class CommodityGetDto {
    private int status;     //状态标识
    private String info;    //状态信息
    private List<Commodity> commodityList;      //查询出来的商品集合
    private Commodity commodity;                //查询出来的单个商品

    public CommodityGetDto() {
    }

    public CommodityGetDto(StatusEnum statusEnum, List<Commodity> commodityList) {
        this.status = statusEnum.getStatus();
        this.info = statusEnum.getInfo();
        this.commodityList = commodityList;
    }

    public CommodityGetDto(StatusEnum statusEnum, Commodity commodity) {
        this.status = statusEnum.getStatus();
        this.info = statusEnum.getInfo();
        this.commodity = commodity;
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

    public List<Commodity> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<Commodity> commodityList) {
        this.commodityList = commodityList;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    @Override
    public String toString() {
        return "CommodityGetDto{" +
                "status=" + status +
                ", info='" + info + '\'' +
                ", commodityList=" + commodityList +
                ", commodity=" + commodity +
                '}';
    }
}
