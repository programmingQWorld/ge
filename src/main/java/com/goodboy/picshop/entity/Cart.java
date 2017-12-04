package com.goodboy.picshop.entity;

/**
 * 购物车实体类
 */
public class Cart {
    private int id;                 //购物车记录id
    private User user;              //所属用户实体，多对一复合属性
    private Commodity commodity;    //购物车里的商品实体，多对一复合属性

    public Cart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    @Override
    public String toString() {
        return "Cart{ id = " + this.id + ", user = " + this.user + ", commodity = " + this.commodity + " }";
    }
}
