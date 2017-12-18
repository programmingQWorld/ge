package com.goodboy.picshop.service.impl;

import com.goodboy.picshop.dao.CartDao;
import com.goodboy.picshop.dao.CartItemDao;
import com.goodboy.picshop.dto.CartDto;
import com.goodboy.picshop.entity.Cart;
import com.goodboy.picshop.entity.CartItem;
import com.goodboy.picshop.entity.User;
import com.goodboy.picshop.service.CartItemService;
import com.goodboy.picshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service("cartService")
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDao cartDao;
	@Autowired
	private CartItemDao cartItemDao;
	@Autowired
	private CartItemService cartItemService;

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

	/**
	 * 保存用户购物车信息
	 * @param userid  购物车车主id
	 * @param cartDto  购物车dto 会用到里面的cartid, itemlist
	 */
	@Override
	public void saveUserCartInfo(int userid, CartDto cartDto) {
		try {
			int result = 0;
			// 如果cart.id = 0, 数据库中插入购物车
			if ( cartDto.getCartid() == 0) {
				Cart cart = new Cart();
				User user = new User();
				user.setId(1);
				cart.setUser( user );
				cartDao.saveUserCartInfo(cart);  // 购物车对应的那条记录
				cartDto.setCartid(cart.getId());
			}
			if ( result == 0) {  // 说明这个购物车里面存在商品
				cartItemService.clearCart(cartDto.getCartid());
			}
			cartItemService.saveCartItemList(cartDto.getCartid(), cartDto.getItems()); // 缓存购物项到 shop_cart_items 表
		} catch (Exception e) {
			e.printStackTrace();  // 外键引用的用户不存在
		}
	}
}
