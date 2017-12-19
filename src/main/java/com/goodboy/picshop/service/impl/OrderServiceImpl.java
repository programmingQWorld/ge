package com.goodboy.picshop.service.impl;

import com.goodboy.picshop.dao.OrderDao;
import com.goodboy.picshop.dto.JSONResult;
import com.goodboy.picshop.dto.OrderDto;
import com.goodboy.picshop.dto.PayDto;
import com.goodboy.picshop.dto.StatusEnum;
import com.goodboy.picshop.entity.Commodity;
import com.goodboy.picshop.entity.Order;
import com.goodboy.picshop.entity.Receiving;
import com.goodboy.picshop.entity.User;
import com.goodboy.picshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;
	/**
	 * 查找买家的所有订单
	 * @return 数据传输对象 orderdto
	 */
	@Override
	public OrderDto queryBuyerOrders( int buyerid, int offset, int limit ) {
		List<Order> list = orderDao.queryBuyerOrderById(buyerid, offset, limit,  null);  // db
		if ( !list.isEmpty() ) {
			OrderDto orderDto = new OrderDto(StatusEnum.SUCCESS, list);
			return orderDto;
		}
		return null;
	}

	@Override
	public OrderDto deleteByOrderId(int orderid, int userid) {
		int result = orderDao.deleteByOrderId(orderid, userid);
		if (result == 0) { // 删除失败
			return new OrderDto(StatusEnum.DEL_ORDER_FAILD);
		}
		// 删除成功
		OrderDto dto = new OrderDto();
		return null;
	}

	// 创建订单信息
	@Override
	public OrderDto insertOrders(Integer[] commids, int rid, int userid) {

		// 将参数都封装为 List<Order>的形式
		Receiving receiving = new Receiving();
		receiving.setId(rid);
		User user = new User();
		user.setId(userid);
		List<Order> orders = new ArrayList<>();
		List<Integer> expList = new ArrayList();
		for ( int i=0; i<commids.length; i++  ) {
			Commodity commodity = new Commodity();
			commodity.setId(commids[i]);
			Order o = new Order();
			o.setIsPay(0);
			o.setStatus( 0 );
			o.setUser( user );
			o.setReceiving( receiving );
			o.setCreateTime(new Date());
			o.setCommodity(commodity);
			o.setOrderNo( generateOrderNo(userid) );

			try {
				orderDao.insertOrder(o);
			} catch (DuplicateKeyException dke) {
				expList.add( o.getCommodity().getId() );
			} catch (DataIntegrityViolationException dlve) {
				expList.add( o.getCommodity().getId() );
			}
		}
		OrderDto orderDto = null;
		if ( expList.isEmpty()  ) {  // 生成订单成功
			orderDto =  new OrderDto(StatusEnum.SUCCESS);
		} else { // 生成订单失败
			orderDto = new OrderDto(StatusEnum.INS_ORDER_FAILD);
			orderDto.setExpList(expList);
		}
		return orderDto;
	}

	/**
	 * 支付
	 * @param userid  买家
	 * @param commid 商品id
	 * @return PayDto对象
	 */
	@Override
	public PayDto buyerPay(int userid, int commid) {
		int res = orderDao.pay(userid, commid);
		if ( res == 0 ) {
			return new PayDto(StatusEnum.PAY_FAILED);
		}
		return new PayDto(StatusEnum.PAY_SUCCESS);
	}

	// 根据卖家id查询订单信息
	@Override
	public OrderDto querySellerOrdersBySellerid(int sellerid, int offset, int limit) {
		List<Order> orders = orderDao.querySellerOrderBySellerID(sellerid, offset, limit, null);
		if ( orders.isEmpty()) {  // 没有订单
			return new OrderDto(StatusEnum.NONE_ORDERS, orders);
		}
		return new OrderDto(StatusEnum.SUCCESS, orders);
	}

	// 根据卖家id查询订单信息( 再按照 未发货，已发货来过滤 )
	@Override
	public OrderDto querySellerOrdersBySelleridAndStatus(int sellerid, int offset, int limit, int status) {
		List<Order> orders = orderDao.querySellerOrderBySellerID(sellerid, offset, limit, status);
		if ( orders.isEmpty()) {  // 没有订单
			return new OrderDto(StatusEnum.NONE_ORDERS, orders);
		}
		return new OrderDto(StatusEnum.SUCCESS, orders);
	}

	/**
	 * 设定订单的配送状态
	 * @param status
	 * @param orderid
	 * @return
	 */
	@Override
	public OrderDto handlerSellerOrder(int status, int orderid) {
		OrderDto dto = null;
		int result = orderDao.checkPay(orderid);
		if (result == 0) {  // 该订单未付款
			dto = new OrderDto(StatusEnum.NOT_PAY_YET);
			return dto;
		}
		result = orderDao.handlerOrder(status, orderid);
		if ( result == 0) {  // 修改失败
			dto = new OrderDto(StatusEnum.ORDER_UNKNOW);
			List<Integer> list = new ArrayList<>();
			list.add(orderid);
			dto.setExpList( list );
			return dto;
		}
		return new OrderDto(StatusEnum.SUCCESS);
	}
	/**
	 * 根据用户的状态查找已付款和未付款的订单
	 * @param userid  用户id
	 * @param offset 偏移量
	 * @param limit 数量
	 * @param payStatus 支付状态（0，1）
	 * @return
	 */
	@Override
	public OrderDto getOrdersByPayStatus(int userid, int offset, int limit, int payStatus) {
		List<Order> list = orderDao.queryBuyerOrderById(userid, offset, limit, payStatus);
		if ( !list.isEmpty() ) {
			OrderDto orderDto = new OrderDto(StatusEnum.SUCCESS, list);
			return orderDto;
		}
		return new OrderDto(StatusEnum.NONE_ORDERS);
	}

	// 生成订单编号
	public static String generateOrderNo(int userid) {
		long timestamp = new Date().getTime();
		return "" + timestamp + "" + userid + "";
	}

}
