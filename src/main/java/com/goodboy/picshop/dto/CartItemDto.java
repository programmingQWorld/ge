package com.goodboy.picshop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude;

public class CartItemDto {
	private int commid;   // 商品id
	private String commName;  // 商品名
	private float price;
	private String sallerName;   // 老板姓名
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String sallerPhone = "";  // 老板的联系电话

	public CartItemDto(int commid, String commName, float price, String sallerName) {
		this.commid = commid;
		this.commName = commName;
		this.price = price;
		this.sallerName = sallerName;
	}

	public String getSallerPhone() {
		return sallerPhone;
	}

	public void setSallerPhone(String sallerPhone) {
		this.sallerPhone = sallerPhone;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CartItemDto that = (CartItemDto) o;

		if (commid != that.commid) return false;
		if (Float.compare(that.price, price) != 0) return false;
		if (!commName.equals(that.commName)) return false;
		if (!sallerName.equals(that.sallerName)) return false;
		return sallerPhone.equals(that.sallerPhone);
	}

	public CartItemDto() {

	}


	public int getCommid() {
		return commid;
	}

	public void setCommid(int commid) {
		this.commid = commid;
	}

	public String getCommName() {
		return commName;
	}

	public void setCommName(String commName) {
		this.commName = commName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getSallerName() {
		return sallerName;
	}

	public void setSallerName(String sallerName) {
		this.sallerName = sallerName;
	}

	@Override
	public String toString() {
		return "CartItemDto{" +
				"commid=" + commid +
				", commName='" + commName + '\'' +
				", price=" + price +
				", sallerName='" + sallerName + '\'' +
				'}';
	}
}
