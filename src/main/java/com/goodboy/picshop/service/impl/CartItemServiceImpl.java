package com.goodboy.picshop.service.impl;

import com.goodboy.picshop.dto.CartDto;
import com.goodboy.picshop.dto.CartItemDto;
import com.goodboy.picshop.entity.CartItem;
import com.goodboy.picshop.entity.Commodity;
import com.goodboy.picshop.service.CartItemService;
import org.springframework.stereotype.Service;

@Service("cartItemService")
public class CartItemServiceImpl implements CartItemService {

	//public Cart addCartItem(Cart cart, Commodity commodity) {
	public CartDto addCartItem(CartDto cartDto, Commodity commodity) {

		boolean isHave = false;
		// 拿到当前的购物项( 商品到购物项的转换 )
		CartItem cartItem = commToCartItem(commodity);
		CartItemDto itemDto = cartItem.toDto();  // 要经过判断才能决定是否添加到购物车中
		for (CartItemDto dto : cartDto.getItems()) {
			if ( dto.getCommid() == itemDto.getCommid() ) {  // 商品已经存在与您的购物车
				isHave = true;
				break;
			}
		}
		if ( !isHave ) {  // 当前购物项在购物车中不存在，新添加即可
			cartDto.getItems().add(itemDto);
		}
		return cartDto;
	}

	public CartItem commToCartItem(Commodity commodity) {
		CartItem cartItem = new CartItem();
		cartItem.setCommodity(commodity);
		return cartItem;
	}
}
