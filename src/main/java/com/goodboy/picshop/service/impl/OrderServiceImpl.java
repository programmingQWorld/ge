package com.goodboy.picshop.service.impl;

import com.goodboy.picshop.dao.OrderDao;
import com.goodboy.picshop.dto.OrderDto;
import com.goodboy.picshop.dto.StatusEnum;
import com.goodboy.picshop.entity.Commodity;
import com.goodboy.picshop.entity.Order;
import com.goodboy.picshop.entity.Receiving;
import com.goodboy.picshop.entity.User;
import com.goodboy.picshop.service.OrderService;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
		List<Order> list = orderDao.queryBuyerOrderById(buyerid, offset, limit);  // db
		if ( !list.isEmpty() ) {
			OrderDto orderDto = new OrderDto(StatusEnum.SUCCESS, list);
			return orderDto;
		}
		return null;
	}

	@Override
	public OrderDto deleteByOrderId(int orderid) {
		int result = orderDao.deleteByOrderId(orderid);
		if (result == 0) { // 删除失败
			return new OrderDto(StatusEnum.DEL_ORDER_FAILD);
		}
		// 删除成功
		OrderDto dto = new OrderDto();
		return null;
	}

	@Override
	public OrderDto insertOrders(List<Integer> commids, int rid, int userid) {

		// 将参数都封装为 List<Order>的形式
		Receiving receiving = new Receiving();
		receiving.setId(rid);
		User user = new User();
		user.setId(userid);
		List<Order> orders = new ArrayList<>();
		List<Integer> expList = new ArrayList();
		for ( int i=0; i<commids.size(); i++  ) {
			Commodity commodity = new Commodity();
			commodity.setId(commids.get(i));
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

	public static String generateOrderNo(int userid) {
		long timestamp = new Date().getTime();
		return "" + timestamp + "" + userid + "";
	}

}
