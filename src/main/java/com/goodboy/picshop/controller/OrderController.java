package com.goodboy.picshop.controller;

import com.goodboy.picshop.dto.JSONResult;
import com.goodboy.picshop.dto.OrderDto;
import com.goodboy.picshop.entity.User;
import com.goodboy.picshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("order")
public class OrderController {

	@Autowired
	private OrderService orderService;


	/**
	 * 获得买家订单列表
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET )
	public JSONResult<OrderDto> orderList (@RequestParam(value = "offset", defaultValue = "0") int offset,
	                                                              @RequestParam(value = "limit", defaultValue = "4") int limit, HttpSession session) {
		User userOnline = (User)session.getAttribute("user");
		//int buyerid = userOnline.getId();
		int buyerid = 1;
		OrderDto dto = null;
		dto =  orderService.queryBuyerOrders(buyerid, offset, limit);
		return new JSONResult<OrderDto>(true, dto);
	}

	// 删除订单
	@RequestMapping("del/{orderid}")
	public JSONResult<OrderDto> delOrderBuOrderId (@PathVariable("orderid")int orderid, HttpSession session) {

		User user = (User)session.getAttribute("user");
		OrderDto dto = orderService.deleteByOrderId(orderid);
		if (dto != null) {
			return new JSONResult<OrderDto>(false, dto);
		}
		return new JSONResult<>(true, "删除成功");
	}

	@RequestMapping("addOrder")
	public JSONResult<OrderDto> addOrder (@PathVariable("commids") int[] commids, HttpSession session) {
		User userOnline = (User)session.getAttribute("user");
		//Receiving rec =  receivingService.getDefaultRec( user.getId() );
		return null;
	}
}