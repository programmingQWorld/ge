package com.goodboy.picshop.service;

import com.goodboy.picshop.dto.OrderDto;

import java.util.List;

/**
 * 订单业务接口
 */
public interface OrderService {

	/**
	 * 查找买家的所有订单
	 * @param userid  用户（买家）id
	 * @param offset 偏移量
	 * @param limit
	 * @return 数据传输对象 orderdto
	 */
	public OrderDto queryBuyerOrders(int userid, int offset, int limit);

	/**
	 * 根据id删除订单信息
	 * @param orderid  订单id号
	 * @return  包含删除成功的信息
	 */
	public OrderDto deleteByOrderId(int orderid);
	public OrderDto insertOrders(List<Integer>commids, int rid, int userid);

}
