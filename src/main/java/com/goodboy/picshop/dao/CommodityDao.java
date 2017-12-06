package com.goodboy.picshop.dao;

import com.goodboy.picshop.entity.Commodity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityDao {

    /**
     * 通过用户id查询其所有上架商品，根据上架时间倒序排序（我的商品）
     * @param userId 用户id
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 用户上架商品集合
     */
    List<Commodity> queryCommodityByUserId(@Param("userId") int userId, @Param("offset") int offset, @Param("limit") int limit);

    /**
     * 插入新商品记录（增加商品）
     * @param commodity 新商品实体
     * @return 插入行数
     */
    int insertCommodity(Commodity commodity);

    /**
     * 根据商品上架时间倒序查询所有商品（作品页）
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 所有商品集合
     */
    List<Commodity> queryAllCommodity(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 通过商品id查询单个商品（商品详情）
     * @param id 商品id
     * @return 单个商品实体
     */
    Commodity queryCommodityById(int id);

    /**
     * 查询某标签下的最新作品
     * @param tagId 标签id
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 商品集合
     */
    List<Commodity> queryCommodityByTagId(@Param("tagId") int tagId, @Param("offset") int offset, @Param("limit") int limit);

    /**
     * 插入标签与商品关联的记录
     * @param tagId 标签id
     * @param commodityId 商品id
     * @return 插入行数
     */
    int insertCommodityRelTag(@Param("tagId") int tagId, @Param("commodityId") int commodityId);
}
