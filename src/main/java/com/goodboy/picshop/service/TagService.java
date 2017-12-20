package com.goodboy.picshop.service;

import com.goodboy.picshop.dto.TagDto;
import com.goodboy.picshop.entity.Tag;

import java.util.List;

/**
 * 标签业务接口
 */
public interface TagService {
    /**
     * 获取所有标签
     * @return 标签的数据传输对象
     */
    TagDto getAll();

    /**
     * 新增标签
     * @param name 标签名称
     * @return 标签的数据传输对象
     */
    TagDto add(String name);

    /**
     * 根据商品id获取该商品标签
     * @param commodityId 商品id
     * @return 标签的数据传输对象
     */
    TagDto getByCommodityId(int commodityId);
}
