package com.goodboy.picshop.entity;

import java.util.Date;

/**
 * 订单实体类
 */
public class Order {
    private int id;             //订单id
    private int isPay;          //是否付款
    private String orderNo;     //订单号
    private Date createTime;    //订单创建时间
    private int status;         //订单发货状态
    private User user;          //所属用户实体，多对一
    private Receiving receiving;    //订单的收货信息实体
    private Commodity commodity;    //订单的商品实体

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsPay() {
        return isPay;
    }

    public void setIsPay(int isPay) {
        this.isPay = isPay;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Receiving getReceiving() {
        return receiving;
    }

    public void setReceiving(Receiving receiving) {
        this.receiving = receiving;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    @Override
    public String toString() {
        return "Order{ id = " + this.id
                + ", isPay = " + this.isPay
                + ", orderNo = " + this.orderNo
                + ", createTime = " + this.createTime
                + ", status = " + this.status
                + ", user = " + this.user
                + ", receiving = " + this.receiving
                + ", commodity = " + this.commodity + " }";
    }
}
