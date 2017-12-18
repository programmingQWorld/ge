package com.goodboy.picshop.service.impl;

import com.goodboy.picshop.BaseTest;
import com.goodboy.picshop.dto.TagDto;
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
        TagDto tagDto = tagService.getAll();
        System.out.println(tagDto);
    }

    @Test
    public void testAdd() throws Exception{
        TagDto tagDto = tagService.add("山水画");
        System.out.println(tagDto);
    }
}
