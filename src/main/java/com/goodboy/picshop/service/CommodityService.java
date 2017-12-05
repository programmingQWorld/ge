package com.goodboy.picshop.service;

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
     * @return
     */
    List<Commodity> getByTag(int tagId, int offset, int limit);

    /**
     * 查询所有作品，根据作品上架时间倒序排序
     * @param offset
     * @param limit
     * @return
     */
    List<Commodity> getAll(int offset, int limit);

    /**
     * 根据商品id查询单个作品
     * @param commodityId
     * @return
     */
    Commodity getById(int commodityId);
}
