package com.goodboy.picshop.service;

import com.goodboy.picshop.dto.CartDto;
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

	/**
	 * 根据用户id获得购物车信息
	 * @param userid
	 * @return
	 */
	public CartDto getCartInfoByUserId(int userid);

	/**
	 * 保存用户购物车信息
	 * @param userid  购物车车主id
	 * @param cartDto  购物车dto 会用到里面的cartid, itemlist
	 */
	public void saveUserCartInfo(int userid, CartDto cartDto);
}

