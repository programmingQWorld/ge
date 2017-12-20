package com.goodboy.picshop.service;

import com.goodboy.picshop.dto.ReceivingDto;
import com.goodboy.picshop.entity.Receiving;

public interface ReceivingService {
    /**
     * 新增地址
     * @param receiving
     * @return
     */
    ReceivingDto insertReceiving(Receiving receiving);

    /**
     * 删除地址
     * @param id
     * @return
     */
    ReceivingDto delete(int id);

    /**
     * 通过用户id查询其所有收货地址
     * @param userId
     * @return 收货地址集合
     */
    ReceivingDto queryReceivingByUserId(int userId);

    /**
     * 通过用户id查询默认地址
     * @param userId
     * @return 默认收货地址实体
     */
    ReceivingDto queryDefaultReceiving(int userId);

    /**
     * 设置用户默认地址
     * @param id
     * @param isDefault
     * @return 影响行数
     */
    ReceivingDto setIsDefault(int id,int isDefault);

}
