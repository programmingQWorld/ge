package com.goodboy.picshop.service.impl;

import com.goodboy.picshop.BaseTest;
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
        List<Commodity> commodityList = commodityService.getByTag(1, 0, 5);
        System.out.println(commodityList);
    }

    @Test
    public void testGetAll() throws Exception{
        List<Commodity> commodityList = commodityService.getAll(12, 12);
        System.out.println(commodityList);
    }

    @Test
    public void testGetById() throws Exception{
        Commodity commodity = commodityService.getById(1);
        System.out.println(commodity);
    }
}
