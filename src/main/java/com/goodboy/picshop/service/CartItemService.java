package com.goodboy.picshop.service;

import com.goodboy.picshop.dto.CartDto;
import com.goodboy.picshop.entity.CartItem;
import com.goodboy.picshop.entity.Commodity;

/**
 * 购物车中的商品项
 */
public interface CartItemService  {
	/**
	 * 添加购物项，返回新的购物车
	 * @param cart  购物车
	 * @param commodity 商品
	 * @return
	 */
	public CartDto addCartItem(CartDto cart, Commodity commodity);

	/**
	 * 把商品数据转化为购物项
	 * @param commodity 商品
	 * @return 购物项
	 */
	public CartItem commToCartItem (Commodity commodity);
}
