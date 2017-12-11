package com.goodboy.picshop.service.impl;

import com.goodboy.picshop.entity.Cart;
import com.goodboy.picshop.entity.CartItem;
import com.goodboy.picshop.service.CartService;
import org.springframework.stereotype.Service;

@Service("cartService")
public class CartServiceImpl implements CartService {
	/**
	 * 计算购物车总的价格
	 * @param cart  购物车
	 * @return
	 */
	public double calcuTotal(Cart cart) {
		double total = 0.0;
		if (cart.getCartItems().size()>0 ) {
			for (CartItem item : cart.getCartItems() ) {
				total += item.getCommodity().getPrice();
			}
		}
		return total;
	}
}
