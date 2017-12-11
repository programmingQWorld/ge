package com.goodboy.picshop.service.impl;

import com.goodboy.picshop.entity.Cart;
import com.goodboy.picshop.entity.CartItem;
import com.goodboy.picshop.entity.Commodity;
import com.goodboy.picshop.service.CartItemService;
import org.springframework.stereotype.Service;

@Service("cartItemService")
public class CartItemServiceImpl implements CartItemService {

	public Cart addCartItem(Cart cart, Commodity commodity) {

		boolean isHave = false;
		// 拿到当前的购物项
		CartItem cartItem = commToCartItem(commodity);
		for( CartItem item : cart.getCartItems() ) {
			if ( item.getId() == cartItem.getId()) {
				// 商品已经存在与您的购物车
				isHave = true;
				break;
			}
		}
		if (!isHave) {  // 当前购物项在购物车中不存在，新添加即可。
			cart.getCartItems().add( cartItem );
		}
		return cart;
	}

	public CartItem commToCartItem(Commodity commodity) {
		CartItem cartItem = new CartItem();
		cartItem.setCommodity(commodity);
		return cartItem;
	}
}
