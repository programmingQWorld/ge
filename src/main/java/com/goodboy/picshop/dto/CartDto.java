package com.goodboy.picshop.dto;

import com.goodboy.picshop.entity.Cart;

import java.util.List;

public class CartDto extends BaseDto {
	private int cartid;
	private String username;
	private List<CartItemDto> items;

	public CartDto() {
		super();
	}

	public CartDto(int cartid, String username, List<CartItemDto> items) {
		this.cartid = cartid;
		this.username = username;
		this.items = items;
	}

	public CartDto(int status, String info, int cartid, String username, List<CartItemDto> items) {
		super(status, info);
		this.cartid = cartid;
		this.username = username;
		this.items = items;
	}

	@Override
	public String toString() {
		return "CartDto{" +
				"cartid=" + cartid +
				", username='" + username + '\'' +
				", items=" + items +
				'}';
	}

	public int getCartid() {
		return cartid;
	}

	public void setCartid(int cartid) {
		this.cartid = cartid;
	}

	public CartDto(int status, String info) {
		super(status, info);
	}

	public CartDto(int status, String info, String username, List<CartItemDto> items) {
		super(status, info);
		this.username = username;
		this.items = items;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<CartItemDto> getItems() {
		return items;
	}

	public void setItems(List<CartItemDto> items) {
		this.items = items;
	}

}
