package com.goodboy.picshop.service;

import com.goodboy.picshop.dto.CommodityAddDto;
import com.goodboy.picshop.dto.CommodityGetDto;
import com.goodboy.picshop.entity.Commodity;

import java.util.List;

/**
 * 商品业务接口：站在“使用者”的角度设计
 */
public interface CommodityService {
    /**
     * 根据标签id查询该标签下的作品，根据作品上架时间倒序排序
     * @param tagId
     * @param offset
     * @param limit
     * @return 查询商品的数据传输对象
     */
    CommodityGetDto getByTag(int tagId, int offset, int limit);

    /**
     * 查询所有作品，根据作品上架时间倒序排序
     * @param offset
     * @param limit
     * @return 查询商品的数据传输对象
     */
    CommodityGetDto getAll(int offset, int limit);

    /**
     * 根据商品id查询单个作品
     * @param commodityId
     * @return 商品查询的数据传输对象
     */
    CommodityGetDto getById(int commodityId);

    /**
     * 根据卖家id查询该卖家下的所有商品
     * @param userId
     * @param offset
     * @param limit
     * @return
     */
    CommodityGetDto getByUser(int userId, int offset, int limit);

    /**
     * 增加商品
     * @param name
     * @param picture
     * @param tagId
     * @param sizeWidth
     * @param sizeHeight
     * @param price
     * @return
     */
    CommodityAddDto add(String name, String picture, int tagId, float sizeWidth, float sizeHeight, float price);
}
