package com.goodboy.picshop.service;

import com.goodboy.picshop.dto.CommodityDto;

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
    CommodityDto getByTag(int tagId, int offset, int limit);

    /**
     * 查询所有作品，根据作品上架时间倒序排序
     * @param offset
     * @param limit
     * @return 查询商品的数据传输对象
     */
    CommodityDto getAll(int offset, int limit);

    /**
     * 根据商品id查询单个作品
     * @param commodityId
     * @return 商品查询的数据传输对象
     */
    CommodityDto getById(int commodityId);

    /**
     * 根据卖家id查询该卖家下的所有商品
     * @param userId
     * @param offset
     * @param limit
     * @return
     */
    CommodityDto getByUser(int userId, int offset, int limit);

    /**
     * 增加商品
     * @param name
     * @param picture
     * @param tagId
     * @param sizeWidth
     * @param sizeHeight
     * @param price
     * @param userId
     * @return
     */
    CommodityDto add(String name, String picture, int tagId, float sizeWidth, float sizeHeight, float price, int userId);

    /**
     * 根据商品等级获取
     * @param level 商品等级
     * @return
     */
    CommodityDto getByLevel(int level);

    /**
     * 修改商品信息
     * @param id
     * @param name
     * @param price
     * @param sizeWidth
     * @param sizeHeight
     * @return
     */
    CommodityDto update(int id, String name, float price, float sizeWidth, float sizeHeight);
}
