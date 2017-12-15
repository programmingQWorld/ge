package com.goodboy.picshop.controller;

import com.goodboy.picshop.dto.CommodityGetDto;
import com.goodboy.picshop.entity.Cart;
import com.goodboy.picshop.service.CartItemService;
import com.goodboy.picshop.service.CartService;
import com.goodboy.picshop.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class CartItemController {
	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private CartService cartService;
	@Autowired
	private CommodityService commodityService;

}
