package com.goodboy.picshop.service;

import com.goodboy.picshop.entity.Cart;

/**
 * 购物车接口
 */
public interface CartService {
	/**
	 * 计算购物车总价格
	 * @param cart  购物车
	 * @return
	 */
	public double calcuTotal(Cart cart) throws Exception;
}
