package com.goodboy.picshop.entity;

import java.util.Date;

/**
 * 商品实体类
 */
public class Commodity {
    private int id;             //商品id
    private String name;        //商品名称
    private float price;        //商品价格
    private String picture;     //商品图片链接
    private float shippingCost; //运费
    private Date createTime;    //商品上架时间
    private float sizeWidth;    //商品的宽
    private float sizeHeight;   //商品的高
    private User user;          //所属卖家用户实体，多对一复合属性

    public Commodity() {
    }

    public Commodity(String name, float price, String picture, float shippingCost, Date createTime, float sizeWidth, float sizeHeight, User user) {
        this.name = name;
        this.price = price;
        this.picture = picture;
        this.shippingCost = shippingCost;
        this.createTime = createTime;
        this.sizeWidth = sizeWidth;
        this.sizeHeight = sizeHeight;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public float getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(float shippingCost) {
        this.shippingCost = shippingCost;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public float getSizeWidth() {
        return sizeWidth;
    }

    public void setSizeWidth(float sizeWidth) {
        this.sizeWidth = sizeWidth;
    }

    public float getSizeHeight() {
        return sizeHeight;
    }

    public void setSizeHeight(float sizeHeight) {
        this.sizeHeight = sizeHeight;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Commodity{ id = " + this.id
                + ", name = " + this.name
                + ", price = " + this.price
                + ", picture = " + this.picture
                + ", shippingCost = " + this.shippingCost
                + ", createTime = " + this.createTime
                + ", sizeWidth = " + this.sizeWidth
                + ", sizeHeight = " + this.sizeHeight
                + ", user = " + this.user + " }";
    }
}
