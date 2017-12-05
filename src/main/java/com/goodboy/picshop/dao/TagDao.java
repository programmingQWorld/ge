package com.goodboy.picshop.dao;

import com.goodboy.picshop.entity.Tag;

import java.util.List;

public interface TagDao {

    /**
     * 查询所有标签，按id倒序排序
     * @return 标签集合
     */
    List<Tag> queryAll();
}
