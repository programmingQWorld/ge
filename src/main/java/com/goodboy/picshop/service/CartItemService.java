package com.goodboy.picshop.service;

import com.goodboy.picshop.dto.CartDto;
import com.goodboy.picshop.dto.CartItemDto;
import com.goodboy.picshop.entity.CartItem;
import com.goodboy.picshop.entity.Commodity;

import java.util.List;

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

	/**
	 * 缓存购物车中的商品到数据库
	 * @param cartid  购物车id
	 * @param items 购物中的商品集合
	 */
    public void saveCartItemList(int cartid, List<CartItemDto> items);

	/**
	 * 清空数据库中的购物车
	 * @param cartid
	 */
	public void clearCart (int cartid);
}
