package com.goodboy.picshop.service.impl;

import com.goodboy.picshop.dao.CartItemDao;
import com.goodboy.picshop.dto.CartDto;
import com.goodboy.picshop.dto.CartItemDto;
import com.goodboy.picshop.entity.CartItem;
import com.goodboy.picshop.entity.Commodity;
import com.goodboy.picshop.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("cartItemService")
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	private CartItemDao cartItemDao;

	/**
	 * 添加商品到购物车
	 * @param cartDto 购物车dto对象
	 * @param commodity 商品
	 * @return
	 */
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

	/**
	 * 缓存购物车中的商品到数据库
	 * @param cartid  购物车id
	 * @param items 购物中的商品集合
	 */
	@Override
	public void saveCartItemList(int cartid, List<CartItemDto> items) {
		Map<String, Object> map = new HashMap();
		map.put("cartid", cartid);
		map.put("items", items);
		clearCart(cartid);
		cartItemDao.saveCartItemList(map);
	}
	/**
	 * 清空数据库中的购物车
	 * @param cartid
	 */
	@Override
	public void clearCart(int cartid) {
		cartItemDao.clearCartItemsByCartid(cartid);
	}
}
