package com.goodboy.picshop.service.impl;

import com.goodboy.picshop.dao.TagDao;
import com.goodboy.picshop.dto.StatusEnum;
import com.goodboy.picshop.dto.TagDto;
import com.goodboy.picshop.entity.Tag;
import com.goodboy.picshop.exception.TagRepeatException;
import com.goodboy.picshop.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    //注入依赖
    @Autowired
    private TagDao tagDao;

    public TagDto getAll() {
        List<Tag> tagList = tagDao.queryAll();
        TagDto tagDto = new TagDto(StatusEnum.SUCCESS, tagList);
        return tagDto;
    }

    // 新增标签
    public TagDto add(String name) {
        Tag tag = new Tag(name);
        try {
            // 执行插入标签操作
            int insert = tagDao.insertTag(tag);
            if(insert >= 1){    // 插入标签成功
                TagDto tagDto = new TagDto(StatusEnum.SUCCESS);
                return tagDto;
            }
        }catch (DuplicateKeyException dke){     // 捕捉键值重复异常，再抛出自定义标签重复异常
            throw new TagRepeatException("tag repeat");
        }
        return null;
    }
}
