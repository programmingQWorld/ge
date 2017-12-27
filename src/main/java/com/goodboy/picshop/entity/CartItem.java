package com.goodboy.picshop.entity;

import com.goodboy.picshop.dto.CartItemDto;

public class CartItem {
		private int id;
		private Commodity commodity;
		private Cart cart;

	/**
	 * 生成dto ( 商品id, 商品名称，商品价格， 商品老板姓名 )
	 * @return  cartitem类的dto
	 */
	public CartItemDto toDto () {
		CartItemDto dto = new CartItemDto();
		dto.setCommid(this.getCommodity().getId());
		dto.setCommName(this.getCommodity().getName());
		dto.setPic(this.getCommodity().getPicture());
		dto.setPrice(this.getCommodity().getPrice());
		dto.setSallerName(this.getCommodity().getUser().getNickname());
		return dto;
	}

	public CartItem() {
	}


	@Override
	public String toString() {
		return "CartItem{" +
				"id=" + id +
				", commodity=" + commodity +
				", cart=" + cart +
				'}';
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
}
