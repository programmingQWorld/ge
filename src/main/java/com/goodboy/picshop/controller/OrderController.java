package com.goodboy.picshop.controller;

import com.goodboy.picshop.dto.*;
import com.goodboy.picshop.entity.Order;
import com.goodboy.picshop.entity.User;
import com.goodboy.picshop.service.OrderService;
import com.goodboy.picshop.service.ReceivingService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private ReceivingService receivingService;

	/**
	 * 获得买家订单列表
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET )
	public JSONResult<OrderDto> orderList (@RequestParam(value = "offset", defaultValue = "0") int offset,
										    @RequestParam(value = "limit", defaultValue = "4") int limit, HttpSession session) {
		User userOnline = (User)session.getAttribute("user");
		int buyerid = userOnline.getId();
		OrderDto dto = null;
		dto =  orderService.queryBuyerOrders(buyerid, offset, limit);
		return new JSONResult<OrderDto>(true, dto);
	}

	// 删除订单
	@RequestMapping("del/{orderid}")
	public JSONResult<OrderDto> delOrderBuOrderId (@PathVariable("orderid")int orderid, HttpSession session) {

		User user = (User)session.getAttribute("user");
		if (user == null) {
			OrderDto dto = new OrderDto(StatusEnum.NO_DEL_ORDER_PRI);
			return new JSONResult<OrderDto>(false, dto);
		}
		OrderDto dto = orderService.deleteByOrderId(orderid, user.getId());
		if (dto != null) {
			return new JSONResult<OrderDto>(false, dto);
		}
		return new JSONResult<>(true, "删除成功");
	}

	/**
	 * 创建订单接口 （只有在线用户可以创建订单）
	 * @param commids  订单关联的商品id集合
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "addOrder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public JSONResult<OrderDto> addOrder (Integer[] commids, HttpSession session ) {
		User userOnline = (User)session.getAttribute(	"user");
		ReceivingDto receivingDto =  receivingService.queryDefaultReceiving(userOnline.getId());
		OrderDto orderDto = orderService.insertOrders(commids, receivingDto.getReceiving().getId(), userOnline.getId());
		return new JSONResult<OrderDto>(true, orderDto);
	}

	@RequestMapping(value = "pay/{cno}", method = RequestMethod.GET)
	public JSONResult<PayDto> pay (@PathVariable("cno") int commid, HttpSession session) {
		User userOnline = (User)session.getAttribute(	"user");
		PayDto payDto =orderService.buyerPay(userOnline.getId(), commid);
		return new JSONResult<PayDto>(true, payDto);
	}

	/**
	 * 查找卖家的订单
	 * @param limit
	 * @param offset
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "seller/list", method = RequestMethod.GET)
	public JSONResult<OrderDto> sellerOrders (@RequestParam(value = "offset", defaultValue = "0") int offset,
	                                                                               @RequestParam(value="limit", defaultValue = "4") int limit, HttpSession session) {
		User userOnline = (User) session.getAttribute("user");
		OrderDto dto = orderService.querySellerOrdersBySellerid(userOnline.getId(), offset, limit);
		if ( !dto.getOrderList().isEmpty() ) {  // 存入session中
			session.setAttribute("sellerorder", dto.getOrderList());
		}
		if ( dto.getOrderList().isEmpty() ) {
			return new JSONResult<OrderDto>(false, dto);
		}
		return new JSONResult<OrderDto>(true, dto);
	}

	/**
	 * 卖家受理用户的订单（发货）
	 * @param orderid  订单id
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "seller/handlerorder/{cno}", method = RequestMethod.GET)
	public JSONResult<OrderDto> sendComm (@PathVariable("cno") int orderid, HttpSession session) {
		List<Order> list = (List<Order>)session.getAttribute("sellerorder");
		if ( list == null || list.isEmpty() ) {
			OrderDto dto = new OrderDto(StatusEnum.ORDER_UNKNOW);
			return new JSONResult<OrderDto>(false, dto );
		}
		boolean flag = false;
		for (int i=0;i<list.size(); i++) {
			if (list.get(i).getId() == orderid) {
				flag = true;
			}
		}
		if (!flag) {  // 出错信息，用户修改的信息不存在或超出该用户的修改范围
			OrderDto dto = new OrderDto(StatusEnum.ORDER_UNKNOW);
			return new JSONResult<OrderDto>(false, dto);
		}
		// 执行处理订单操作
		OrderDto dto = orderService.handlerSellerOrder(1, orderid);
		flag = false;
		switch (dto.getStatus()) {
			case 1:
				flag = true;
				break;
			case  3006:
				flag = false;
				break;
			case 3007:
				flag = false;
				break;
		}
		return new JSONResult<OrderDto>(flag, dto);
	}

	/**
	 * 未付款
	 * @return
	 */
	@RequestMapping( value = "unpay" )
	public JSONResult<OrderDto> getBuyerUnPayOrder (@RequestParam(value="offset", defaultValue = "0") int offset,
	                                                                                                 @RequestParam(value = "limit", defaultValue = "4")int limit,
	                                                                                                  HttpSession  session) {
		User userOnline = (User) session.getAttribute("user");
		OrderDto dto = orderService.getOrdersByPayStatus(userOnline.getId(), offset, limit, 0);
		boolean flag = false;
		switch ( dto.getStatus() ) {
			case 1:
				flag = true;
				break;
			case 3005:
				flag = false;
		}
		return new JSONResult<OrderDto>(flag, dto);
	}

	/**
	 * 已付款
	 * @return
	 */
	@RequestMapping( value = "paid" )
	public JSONResult<OrderDto> getBuyerPaidOrder(@RequestParam(value="offset", defaultValue = "0") int offset,
	                                                                            @RequestParam(value = "limit", defaultValue = "4") int limit,
	                                                                                 HttpSession  session) {
		User userOnline = (User) session.getAttribute("user");
		OrderDto dto = orderService.getOrdersByPayStatus(userOnline.getId(), offset, limit, 1);
		boolean flag = false;
		switch ( dto.getStatus() ) {
			case 1:
				flag = true;
				break;
			case 3005:
				flag = false;
		}
		return new JSONResult<OrderDto>(flag, dto);
	}

	@RequestMapping(value = "seller/unsent", method=RequestMethod.GET)
	public JSONResult<OrderDto> sellerUnSentOrder (@RequestParam(value="offset", defaultValue = "0") int offset,
	                                                                            @RequestParam(value = "limit", defaultValue = "4") int limit,
	                                                                            HttpSession session) {
		User userOnline = (User) session.getAttribute("user");
		OrderDto dto = orderService.querySellerOrdersBySelleridAndStatus(userOnline.getId(), offset, limit, 0);
		boolean flag = false;
		switch ( dto.getStatus() ) {
			case 1:
				flag = true;
				break;
			case 3005:
				flag = false;
		}
		return new JSONResult<OrderDto>(flag, dto);
	}

	@RequestMapping(value = "seller/sending", method=RequestMethod.GET)
	public JSONResult<OrderDto> sellerSendingOrder (@RequestParam(value="offset", defaultValue = "0") int offset,
	                                                                             @RequestParam(value = "limit", defaultValue = "4") int limit,
	                                                                             HttpSession session) {
		User userOnline = (User) session.getAttribute("user");
		OrderDto dto = orderService.querySellerOrdersBySelleridAndStatus(userOnline.getId(), offset, limit, 1);
		boolean flag = false;
		switch ( dto.getStatus() ) {
			case 1:
				flag = true;
				break;
			case 3005:
				flag = false;
		}
		return new JSONResult<OrderDto>(flag, dto);
	}
}