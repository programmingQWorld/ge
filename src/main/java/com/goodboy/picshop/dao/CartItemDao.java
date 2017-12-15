package com.goodboy.picshop.dao;

import com.goodboy.picshop.entity.CartItem;

import java.util.List;

/**
 * 操作购物车中的商品信息
 * author: lcq
 */
public interface CartItemDao {

	/**
	 * 查询购物车里面的商品
	 * @param id
	 * @return
	 */
	public List<CartItem> queryCartItemsByCartId(int id);
}
