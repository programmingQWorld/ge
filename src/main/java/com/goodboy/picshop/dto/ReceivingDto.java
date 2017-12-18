package com.goodboy.picshop.dto;


import com.goodboy.picshop.entity.Receiving;

import java.util.List;

/**
 * 封装地址执行的结果
 */
public class ReceivingDto {
    private int status;                 // 标识
    private String info;                // 标识信息
    private List<Receiving> receivingList; //返回用户地址集合
    private Receiving receiving;        //返回用户地址信息


    public ReceivingDto(){
    }
    public ReceivingDto(StatusEnum statusEnum) {
        this.status = statusEnum.getStatus();
        this.info = statusEnum.getInfo();
    }

    public ReceivingDto(StatusEnum statusEnum,List<Receiving> receivingList){
        this.status = statusEnum.getStatus();
        this.info = statusEnum.getInfo();
        this.receivingList=receivingList;
    }

    public ReceivingDto(StatusEnum statusEnum,Receiving receiving) {
        this.status = statusEnum.getStatus();
        this.info = statusEnum.getInfo();
        this.receiving=receiving;
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


    public Receiving getReceiving() {
        return receiving;
    }

    public void setReceiving(Receiving receiving) {
        this.receiving = receiving;
    }

    public List<Receiving> getReceivingList() {
        return receivingList;
    }

    public void setReceivingList(List<Receiving> receivingList) {
        this.receivingList = receivingList;
    }

    public String toString() {
        return "Receiving{status = " + this.status + ", info = " + this.info +
                ", receiving = " + this.receiving + ", receivingList = " + this.receivingList +"}";
    }


}