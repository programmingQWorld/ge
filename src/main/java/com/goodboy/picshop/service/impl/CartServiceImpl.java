package com.goodboy.picshop.service.impl;

import com.goodboy.picshop.dao.CartDao;
import com.goodboy.picshop.dao.CartItemDao;
import com.goodboy.picshop.dto.CartDto;
import com.goodboy.picshop.entity.Cart;
import com.goodboy.picshop.entity.CartItem;
import com.goodboy.picshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cartService")
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDao cartDao;
	@Autowired
	private CartItemDao cartItemDao;

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

	/**
	 * 根据用户id获得购物车信息
	 * @param userid
	 * @return
	 */
	@Override
	public CartDto getCartInfoByUserId(int userid) {
		Cart cart = cartDao.queryCartByUserId(userid);
		if (cart == null) {
			return null;  // 不存在购物车
		}
		List<CartItem> list = cartItemDao.queryCartItemsByCartId(cart.getId());
		cart.setCartItems(list);
		return cart.toDto();
	}

	@Override
	public void saveUserCartInfo(CartDto cartDto) {

	}
}
