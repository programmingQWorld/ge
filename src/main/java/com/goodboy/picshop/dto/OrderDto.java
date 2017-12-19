package com.goodboy.picshop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.goodboy.picshop.entity.Order;

import java.util.List;

public class OrderDto extends BaseDto {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<Order> orderList;  // 查找到的订单列表
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<Integer> expList;  // 错误的商品列表
	public OrderDto(StatusEnum statusEnum) {
		this.setStatus(statusEnum.getStatus());
		this.setInfo(statusEnum.getInfo());
	}
	public OrderDto(StatusEnum statusEnum, List<Order> orderList) {
		this.setStatus(statusEnum.getStatus());
		this.setInfo(statusEnum.getInfo());
		this.orderList = orderList;
	}

	public OrderDto() {
		super();
	}

	@Override
	public String toString() {
		return super.toString() + "OrderDto{" +
				"orderList=" + orderList +
				", expList=" + expList +
				'}';
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public List<Integer> getExpList() {
		return expList;
	}

	public void setExpList(List<Integer> expList) {
		this.expList = expList;
	}
}
