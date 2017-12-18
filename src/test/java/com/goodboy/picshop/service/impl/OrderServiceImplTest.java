package com.goodboy.picshop.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.goodboy.picshop.BaseTest;
import com.goodboy.picshop.dto.OrderDto;
import com.goodboy.picshop.service.OrderService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

public class OrderServiceImplTest extends BaseTest {
	@Autowired
	private OrderService orderService;


	// 根据id获得订单信息，并获得时间戳。
	@Test
	public void testGetAll () {
		OrderDto dto = orderService.queryBuyerOrders(1, 1, 2);
		System.out.println(dto);
		/*  == 测试工作完成 */
		Date timestamp = dto.getOrderList().get(0).getCreateTime();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
		String result = sf.format(timestamp);
		System.out.println(result);
	}

	// 根据id删除订单信息
	@Test
	public void testDelOrderById() {
		orderService.deleteByOrderId(2);
	}

	// 新增订单信息
	@Test
	public void testInsertOrder () {
		List<Integer>list = new ArrayList<>();
		list.add(4);
		list.add(2);
		OrderDto dto = orderService.insertOrders(list, 1, 3);
		System.out.println(dto);
	}
}
