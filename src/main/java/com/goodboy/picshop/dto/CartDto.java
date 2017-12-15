package com.goodboy.picshop.dto;

import com.goodboy.picshop.entity.Cart;

import java.util.List;

public class CartDto extends BaseDto {
	private String username;
	private List<CartItemDto> items;

	public CartDto() {
		super();
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

	@Override
	public String toString() {
		return "CartDto{" +
				"username='" + username + '\'' +
				", items=" + items +
				'}';
	}
}
