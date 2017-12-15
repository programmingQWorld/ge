package com.goodboy.picshop.entity;

import com.goodboy.picshop.dto.CartDto;
import com.goodboy.picshop.dto.CartItemDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 购物车实体类
 */
public class Cart {
    private int id;                 //购物车记录id
    private User user;              //所属用户实体，多对一复合属性
    //private Commodity commodity;    //购物车里的商品实体，多对一复合属性
    private List<CartItem> cartItems = new ArrayList();

    public Cart(List<CartItem> cartItems) {
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

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public CartDto toDto() {
        CartDto dto = new CartDto();
        dto.setUsername(this.user.getNickname());
        List<CartItemDto> list = new ArrayList<>();
        if (getCartItems() != null && getCartItems().size()>0) {
            for(CartItem cartItem :getCartItems()) {
                list.add(cartItem.toDto());
            }
        }
        dto.setItems(list);
        return dto;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", user=" + user +
                ", cartItems=" + cartItems +
                '}';
    }
}