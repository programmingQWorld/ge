package com.goodboy.picshop.controller;

import com.goodboy.picshop.dto.JSONResult;
import com.goodboy.picshop.dto.OrderDto;
import com.goodboy.picshop.dto.StatusEnum;
import com.goodboy.picshop.entity.User;
import com.goodboy.picshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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
	@RequestMapping(value = "addOrder", method = RequestMethod.POST)
	public JSONResult<OrderDto> addOrder (String commids, HttpServletRequest request, HttpSession session ) {
		User userOnline = (User)session.getAttribute(	"user");
		//Receiving rec =  receivingService.getDefaultRec( user.getId() );
		return null;
	}
}