package com.goodboy.picshop.dao;

import com.goodboy.picshop.entity.CartItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

	/**
	 * 保存多个购物车中的商品到购物车
	 * @param map map集合 （ 包含购物车id,购物车中的商品列表 ）
	 */
	void saveCartItemList(@Param("map") Map<String, Object> map);

	/**
	 * 根据购物车id清除购物项信息
	 * @param cartid 购物车id
	 */
	void clearCartItemsByCartid(int cartid);
}
