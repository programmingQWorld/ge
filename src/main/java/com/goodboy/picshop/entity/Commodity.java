package com.goodboy.picshop.entity;

/**
 * 商品实体类
 */
public class Commodity {
    private int id;             //商品id
    private String name;        //商品名称
    private float price;        //商品价格
    private String picture;     //商品图片链接
    private float shippingCost; //运费
    private User user;          //所属卖家用户实体，多对一复合属性

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
                + ", user = " + this.user + " }";
    }
}
