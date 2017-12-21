package com.goodboy.picshop.dao;

import com.goodboy.picshop.entity.Tag;

import java.util.List;

public interface TagDao {

    /**
     * 查询所有标签，按id倒序排序
     * @return 标签集合
     */
    List<Tag> queryAll();

    /**
     * 插入新标签记录
     * @param tag 标签对象实体
     * @return 插入行数
     */
    int insertTag(Tag tag);

    /**
     * 根据商品id查询该商品的标签
     * @param commodityId 商品id
     * @return 标签集合
     */
    List<Tag> queryTagByCommodityId(int commodityId);
}
