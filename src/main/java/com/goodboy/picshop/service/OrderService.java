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
	 * @param orderid 订单id
	 * @param userid 用户id
	 * @return
	 */
	public OrderDto deleteByOrderId(int orderid, int userid);

	/**
	 * 创建订单
	 * @param commids 订单关联的商品id
	 * @param rid 订单关联的收获人id
	 * @param userid 创建订单的用户id
	 * @return
	 */
	public OrderDto insertOrders(List<Integer>commids, int rid, int userid);

}
