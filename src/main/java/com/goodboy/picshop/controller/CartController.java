package com.goodboy.picshop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {

	// 添加商品到购物车
	@RequestMapping("cartAdd")
	public String addUserCart () {
		// 获得购物车，如果没有购物车就新创建一个购物车。
		return "";
	}

}
