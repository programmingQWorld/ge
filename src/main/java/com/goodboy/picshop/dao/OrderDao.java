package com.goodboy.picshop.dao;

import com.goodboy.picshop.dto.OrderDto;
import com.goodboy.picshop.entity.Order;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface OrderDao {

    /**
     * * 插入新的订单记录
     * @param order 订单实体
     * @return 插入行数
     * @throws DuplicateKeyException  cid键重复
     */
    int insertOrder(Order order) throws DuplicateKeyException, DataIntegrityViolationException;

    /**
     * 根据订单id删除订单
     * @param orderid
     * @return
     */
    int deleteByOrderId(@Param("orderid") int orderid, @Param("userid") int userid);

    /**
     * 通过订单id查询单个订单详情
     * @param id 订单id
     * @return 订单实体
     */
    Order queryOrderById(int id);

    /**
     * 根据用户id查询用户的未付款或已付款订单
     * @param userId 用户id
     * @param isPay 是否付款，0未付款，1已付款
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 订单实体集合
     */
    List<Order> queryOrderByUserIdAndIsPay(@Param("userId") int userId,
                                                                   @Param("isPay") int isPay,
                                                                   @Param("offset") int offset,
                                                                   @Param("limit") int limit);

    /**
     * 根据用户id查询买家订单列表
     * @param userId
     * @param offset
     * @param limit
     * @return
     */
    List<Order> queryBuyerOrderById(
            @Param("userId") int userId,
            @Param("offset") int offset,
            @Param("limit") int limit,
            @Param("payStatus") Integer payStatus);

	/**
	 * 买家支付（模拟）
	 * @param userid 买家id
	 * @param commid 商品id
	 * @return
	 */
	int pay(@Param("userid") int userid, @Param("commid") int commid);

	/**
	 * 根据卖家id查询查询订单列表
	 * @param userId  用户id
	 * @param offset
	 * @param limit
	 * @param deliveryStatus 订单的配送状态( 0: 为发货， 1: 已发货 2: 已签收 )
	 * @return
	 */
	List<Order> querySellerOrderBySellerID (@Param("sellerid") int userId,
	                                                            @Param("offset") int offset,
	                                                            @Param("limit") int limit,
	                                                            @Param("deliverystatuts") Integer deliveryStatus
	                                                            );

	int handlerOrder (@Param("status") int status,
	                           @Param("orderid") int orderid );

	int checkPay(int orderid);

}
