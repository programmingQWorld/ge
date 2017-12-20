package com.goodboy.picshop.service;

import com.goodboy.picshop.dto.OrderDto;
import com.goodboy.picshop.dto.PayDto;

import java.util.List;

/**
 * 订单业务接口
 */
public interface OrderService {

	/**
	 * 查找买家的订单
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
	public OrderDto insertOrders(Integer[] commids, int rid, int userid);

	/**
	 * 支付
	 * @param userid  买家
	 * @param commid 商品id
	 * @return PayDto对象
	 */
	PayDto buyerPay(int userid, int commid);

	/**
	 * 根据卖家id来查找属于他的用户列表
	 * @param sellerid  卖家id
	 * @param offset 偏移量
	 * @param limit   查询数量
	 * @return  订单dto对象
	 */
	OrderDto querySellerOrdersBySellerid (int sellerid, int offset, int limit);


	/**
	 *
	 * @param status
	 * @param orderid
	 * @return
	 */
	OrderDto handlerSellerOrder (int status, int orderid);

	/**
	 * 根据用户的状态查找已付款和未付款的订单
	 * @param userid  用户id
	 * @param offset 偏移量
	 * @param limit 数量
	 * @param payStatus 支付状态（0，1）
	 * @return
	 */
	OrderDto getOrdersByPayStatus(int userid, int offset, int limit, int payStatus);

	/**
	 * 根据卖家id查询订单信息( 再按照 未发货，已发货来过滤 )
	 * @param sellerid
	 * @param offset
	 * @param limit
	 * @return
	 */
	OrderDto querySellerOrdersBySelleridAndStatus(int sellerid, int offset, int limit, int status);
}
