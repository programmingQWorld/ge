package com.goodboy.picshop.service.impl;

import com.goodboy.picshop.dao.TagDao;
import com.goodboy.picshop.entity.Tag;
import com.goodboy.picshop.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    //注入依赖
    @Autowired
    private TagDao tagDao;

    public List<Tag> getAll() {
        return tagDao.queryAll();
    }
}
