package com.goodboy.picshop.controller;

import com.goodboy.picshop.dto.CommodityGetDto;
import com.goodboy.picshop.entity.Commodity;
import com.goodboy.picshop.service.CartItemService;
import com.goodboy.picshop.service.CartService;
import com.goodboy.picshop.service.CommodityService;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartItemController {
	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private CartService cartService;
	@Autowired
	private CommodityService commodityService;
	public String addSorder (int commID, HttpServletRequest request) {
		//  根据商品id获取相应的商品数据
		CommodityGetDto = commodityService.getById(commID);

		// 判断当前session是否有购物车，如果没有则创建
		if ( request.getSession() ) {
			return "";
		}
	}

}
