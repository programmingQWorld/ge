package com.goodboy.picshop.service.impl;

import com.goodboy.picshop.BaseTest;
import com.goodboy.picshop.entity.Tag;
import com.goodboy.picshop.service.TagService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TagServiceImplTest extends BaseTest {

    @Autowired
    private TagService tagService;

    @Test
    public void testGetAll() throws Exception{
        List<Tag> tagList = tagService.getAll();
        System.out.println(tagList);
    }
}
