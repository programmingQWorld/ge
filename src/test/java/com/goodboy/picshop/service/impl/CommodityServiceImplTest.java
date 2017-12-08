package com.goodboy.picshop.service.impl;

import com.goodboy.picshop.BaseTest;
import com.goodboy.picshop.dto.CommodityGetDto;
import com.goodboy.picshop.entity.Commodity;
import com.goodboy.picshop.service.CommodityService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommodityServiceImplTest extends BaseTest {
    @Autowired
    private CommodityService commodityService;

    @Test
    public void testGetByTag() throws Exception{
        CommodityGetDto commodityGetDto = commodityService.getByTag(1, 0, 5);
        System.out.println(commodityGetDto);
    }

    @Test
    public void testGetAll() throws Exception{
        CommodityGetDto commodityGetDto = commodityService.getAll(0, 2);
        System.out.println(commodityGetDto);
    }

    @Test
    public void testGetById() throws Exception{
        CommodityGetDto commodityGetDto = commodityService.getById(1);
        System.out.println(commodityGetDto);
    }

    @Test
    public void testGetByUser() throws Exception{
        CommodityGetDto commodityGetDto = commodityService.getByUser(1, 0, 5);
        System.out.println(commodityGetDto);
    }

    @Test
    public void testAdd() throws Exception{
        System.out.println(commodityService.add("q123456234", "http://shierd.info/shio.png", 1, 12, 12, 120));
    }
}
