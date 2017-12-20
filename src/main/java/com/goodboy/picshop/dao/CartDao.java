package com.goodboy.picshop.dao;

import com.goodboy.picshop.entity.Cart;
import com.goodboy.picshop.entity.CartItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DuplicateKeyException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface CartDao {

    /**
     * 通过用户id查询用户的购物车
     * @param userId 用户id
     * @return 购物车实体集合
     */
    Cart queryCartByUserId(int userId);

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

    /**
     * * 在购物车表中插入一条记录记录(根据用户id生成购物车表中的记录)
     * @param cart  根据cart对象获得userid,插入数据。 然后xml配置文件返回自增主键的值到cart.id属性中
     * @return 购物车记录id
     * @throws SQLIntegrityConstraintViolationException 该用户不存在，无法创建购物车
     */
    int saveUserCartInfo(Cart cart) throws DuplicateKeyException;

}
