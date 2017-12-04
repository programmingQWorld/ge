package com.goodboy.picshop.dao;

import com.goodboy.picshop.BaseTest;
import com.goodboy.picshop.entity.Commodity;
import com.goodboy.picshop.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * CommodityDao的测试类
 */
public class CommodityDaoTest extends BaseTest{

    //注入CommodityDao
    @Autowired
    CommodityDao commodityDao;

    //注入UserDao
    @Autowired
    UserDao userDao;

    @Test
    public void testInsertCommodity() throws Exception{
        User user =userDao.queryUserById(1);
        Commodity commodity = new Commodity("hello5555", 1001, "http://goodboy.com/picture.png", 0, user);
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
}
