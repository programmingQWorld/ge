package com.goodboy.picshop.dto;

import com.goodboy.picshop.entity.Commodity;

import java.util.List;

/**
 * 封装操作商品的执行结果
 */
public class CommodityDto extends BaseDto{
    private List<Commodity> commodityList;      //查询出来的商品集合
    private Commodity commodity;                //查询出来的单个商品

    public CommodityDto() {
    }

    public CommodityDto(StatusEnum statusEnum) {
        this.setStatus(statusEnum.getStatus());
        this.setInfo(statusEnum.getInfo());
    }

    public CommodityDto(StatusEnum statusEnum, List<Commodity> commodityList) {
        this.setStatus(statusEnum.getStatus());
        this.setInfo(statusEnum.getInfo());
        this.commodityList = commodityList;
    }

    public CommodityDto(StatusEnum statusEnum, Commodity commodity) {
        this.setStatus(statusEnum.getStatus());
        this.setInfo(statusEnum.getInfo());
        this.commodity = commodity;
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
        return "CommodityDto{" +
                "status=" + this.getStatus() +
                ", info='" + this.getInfo() + '\'' +
                ", commodityList=" + commodityList +
                ", commodity=" + commodity +
                '}';
    }
}
