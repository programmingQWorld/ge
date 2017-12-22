package com.goodboy.picshop.service.impl;

import com.goodboy.picshop.BaseTest;
import com.goodboy.picshop.dto.CommodityDto;
import com.goodboy.picshop.service.CommodityService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CommodityServiceImplTest extends BaseTest {
    @Autowired
    private CommodityService commodityService;

    @Test
    public void testGetByTag() throws Exception{
        CommodityDto commodityDto = commodityService.getByTag(7, 0, 5);
        System.out.println(commodityDto);
    }

    @Test
    public void testGetAll() throws Exception{
        CommodityDto commodityDto = commodityService.getAll(0, 2);
        System.out.println(commodityDto);
    }

    @Test
    public void testGetById() throws Exception{
        CommodityDto commodityDto = commodityService.getById(12);
        System.out.println(commodityDto);
    }

    @Test
    public void testGetByUser() throws Exception{
        CommodityDto commodityDto = commodityService.getByUser(1, 0, 5);
        System.out.println(commodityDto);
    }

    @Test
    public void testAdd() throws Exception{
        CommodityDto commodityDto = commodityService.add("西风", "http://10.10.112.170/images/Original/image6.png",
                6, 120, 120, 120, 2);
        System.out.println(commodityDto);
    }

    @Test
    public void testGetByLevel() throws Exception{
        CommodityDto commodityDto = commodityService.getByLevel(5);
        System.out.println(commodityDto);
    }
}
