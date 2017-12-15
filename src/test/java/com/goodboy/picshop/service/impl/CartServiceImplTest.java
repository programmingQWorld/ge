package com.goodboy.picshop.service.impl;

import com.goodboy.picshop.BaseTest;
import com.goodboy.picshop.dto.CartDto;
import com.goodboy.picshop.service.CartItemService;
import com.goodboy.picshop.service.CartService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CartServiceImplTest extends BaseTest {
	@Autowired
	private CartService cartService;
	/**
	 * 测试获取完整的购物车信息
	 */
	@Test
	public void testGetFullCartInfo () {
		 CartDto dto = cartService.getCartInfoByUserId(1);
		System.out.println(dto);
		System.out.println("---");
	}

	@Test
	public void testMergeList () {
		List<Integer> bl = new ArrayList();
		List<Integer> sl = new ArrayList();

		bl.add(1);
		bl.add(3);
		bl.add(5);

		// sl.add(2);
		// sl.add(4);
		// sl.add(6);

		for ( Integer n : sl ) {
			if (!bl.contains(n)) {
				bl.add(n);
			}
		}
		System.out.println("合并后的数组是：" + bl );
	}
}
