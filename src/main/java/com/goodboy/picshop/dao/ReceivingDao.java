package com.goodboy.picshop.dao;

import com.goodboy.picshop.entity.Receiving;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReceivingDao {

    /**
     * 插入新的收货地址记录（新增地址）
     * @param receiving 收货地址实体
     * @return 插入行数
     */
    int insertReceiving(Receiving receiving);

    /**
     * 通过用户id查询其所有收货地址
     * @param userId 用户id
     * @return 收货地址集合
     */
    List<Receiving> queryReceivingByUserId(int userId);

    /**
     * 设置是否默认（is_default）字段的值
     * @param id 收货地址id
     * @param isDefault 要设置的值
     * @return 影响行数
     */
    int setIsDefault(@Param("id") int id, @Param("isDefault") int isDefault);

    /**
     * 删除收货地址
     * @param id 收货地址id
     * @return 删除行数
     */
    int deleteReceiving(int id);

    /**
     * 查询用户的默认地址（购物车）
     * @param userId 用户id
     * @return 默认收货地址实体
     */
    Receiving queryDefaultReceiving(int userId);
}
