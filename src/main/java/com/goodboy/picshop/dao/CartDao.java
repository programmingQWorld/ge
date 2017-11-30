package com.goodboy.picshop.dao;

import com.goodboy.picshop.entity.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartDao {

    /**
     * 通过用户id查询用户的购物车
     * @param userId 用户id
     * @return 购物车实体集合
     */
    List<Cart> queryCartByUserId(int userId);

    /**
     * 插入新的购物车记录（加入购物车）
     * @param commodityId 商品id
     * @param userId 用户id
     * @return 插入行数
     */
    int insertCart(@Param("commodityId") int commodityId, @Param("userId") int userId);

    /**
     * 根据id删除购物车记录
     * @param id 购物车记录id
     * @return 删除行数
     */
    int deleteCart(int id);
}
