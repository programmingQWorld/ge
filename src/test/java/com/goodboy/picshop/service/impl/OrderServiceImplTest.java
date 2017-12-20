package com.goodboy.picshop.service.impl;

import com.goodboy.picshop.BaseTest;
import com.goodboy.picshop.dto.OrderDto;
import com.goodboy.picshop.dto.PayDto;
import com.goodboy.picshop.entity.Order;
import com.goodboy.picshop.service.OrderService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

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
		OrderDto dto = orderService.queryBuyerOrders(1, 0, 2);
		System.out.println(dto);
		/*  == 测试工作完成 */
		Date timestamp = dto.getOrderList().get(0).getCreateTime();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
		System.out.println(sf.format(timestamp));
	}

	// 根据id删除订单信息
	@Test
	public void testDelOrderById() {
		orderService.deleteByOrderId(8,3);
	}

	// 新增订单信息
	@Test
	public void testInsertOrder () {
		Integer[] list = new Integer[2];
		list[0] = 1;
		list[1] = 2;
		OrderDto dto = orderService.insertOrders(list, 2, 3);
		System.out.println(dto);
	}

	// 支付
	@Test
	public void testPay () {
		PayDto dto = orderService.buyerPay(6, 6);
		System.out.println(dto);
	}

	// 测试： 查询卖家订单
	@Test
	public void testSellerOrders () {
		OrderDto dto = orderService.querySellerOrdersBySellerid(4, 0, 4);
		System.out.println(dto);
	}

	// 测试：发货
	@Test
	public void testSellerHandlerOrder() {
		OrderDto dto = orderService.handlerSellerOrder(0, 66);
		System.out.println(dto);
	}

	// 测试： 查询买家不同支付状态的订单信息
	@Test
	public void testQueryOrderByPayStatus () {
		OrderDto dto = orderService.getOrdersByPayStatus(6, 0, 20, 1);
		System.out.println(dto);

		for( Order o : dto.getOrderList() ) {
			System.out.println(o.getIsPay());
		}
	}
	
	// 测试：查询卖家按不同配送状态分类的订单
	@Test
	public void testQuerySellerStatutsOrders () {
		OrderDto dto =  orderService.querySellerOrdersBySelleridAndStatus(4, 0, 20, 1);
		System.out.println(dto);
		System.out.println(dto.getOrderList().size());
	}
}
