package com.goodboy.picshop.dao;

import com.goodboy.picshop.BaseTest;
import com.goodboy.picshop.entity.Tag;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TagDaoTest extends BaseTest {

    @Autowired
    private TagDao tagDao;

    @Test
    public void testQueryAll() throws Exception{
        List<Tag> tagList = tagDao.queryAll();
        System.out.println(tagList);
    }
}
