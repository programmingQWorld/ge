package com.goodboy.picshop.dao;

import com.goodboy.picshop.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDao {

    /**
     * 通过订单id查询单个订单详情
     * @param id 订单id
     * @return 订单实体
     */
    Order queryOrderById(int id);

    /**
     * 根据用户id查询用户的所有订单
     * @param userId 用户id
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 订单实体集合
     */
    List<Order> queryOrderByUserId(@Param("userId") int userId, @Param("offset") int offset, @Param("limit") int limit);

    /**
     * 根据用户id查询用户的未付款或已付款订单
     * @param userId 用户id
     * @param isPay 是否付款，0未付款，1已付款
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 订单实体集合
     */
    List<Order> queryOrderByUserIdAndIsPay(@Param("userId") int userId, @Param("isPay") int isPay, @Param("offset") int offset, @Param("limit") int limit);
}
