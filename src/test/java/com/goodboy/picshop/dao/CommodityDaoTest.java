package com.goodboy.picshop.dao;

import com.goodboy.picshop.BaseTest;
import com.goodboy.picshop.entity.Commodity;
import com.goodboy.picshop.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * CommodityDao的测试类
 */
public class CommodityDaoTest extends BaseTest{

    //注入CommodityDao
    @Autowired
    private CommodityDao commodityDao;

    //注入UserDao
    @Autowired
    private UserDao userDao;

    @Test
    public void testInsertCommodity() throws Exception{
        User user =userDao.queryUserById(1);
        Commodity commodity = new Commodity("z231094", 1001, "http://goodboy.com/picture.png", 0, new Date(), 12, 12, user);
        int insert = commodityDao.insertCommodity(commodity);
        System.out.println("insert = " + insert);
    }

    @Test
    public void testQueryCommodityByUserId() throws Exception{
        List<Commodity> commodityList = commodityDao.queryCommodityByUserId(1, 0, 5);
        System.out.println(commodityList);
    }

    @Test
    public void testQueryAllCommodity() throws Exception{
        List<Commodity> commodityList = commodityDao.queryAllCommodity(0, 5);
        System.out.println(commodityList);
    }

    @Test
    public void testQueryCommodityById() throws Exception{
        Commodity commodity = commodityDao.queryCommodityById(1);
        System.out.println(commodity);
    }

    @Test
    public void testQueryCommodityByTagId() throws Exception{
        List<Commodity> commodityList = commodityDao.queryCommodityByTagId(1, 0, 5);
        System.out.println(commodityList);
    }

    @Test
    public void testInsertCommodityRelTag() throws Exception{
        int insert = commodityDao.insertCommodityRelTag(1, 4);
        System.out.println("insert = " + insert);
    }
}
