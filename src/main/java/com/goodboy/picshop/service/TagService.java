package com.goodboy.picshop.service;

import com.goodboy.picshop.entity.Tag;

import java.util.List;

/**
 * 标签业务接口
 */
public interface TagService {
    /**
     * 获取所有标签
     * @return
     */
    List<Tag> getAll();
}
