package com.goodboy.picshop.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 购物车实体类
 */
public class Cart {
    private int id;                 //购物车记录id
    private User user;              //所属用户实体，多对一复合属性
    //private Commodity commodity;    //购物车里的商品实体，多对一复合属性
    private Set<CartItem> cartItems = new HashSet();

    public Cart(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

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

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", user=" + user +
                ", cartItems=" + cartItems +
                '}';
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
