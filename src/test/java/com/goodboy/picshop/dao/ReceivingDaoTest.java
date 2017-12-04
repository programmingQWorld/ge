package com.goodboy.picshop.dao;

import com.goodboy.picshop.BaseTest;
import com.goodboy.picshop.entity.Receiving;
import com.goodboy.picshop.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * ReceivingDao的测试类
 */
public class ReceivingDaoTest extends BaseTest {

    //注入ReceivingDao
    @Autowired
    private ReceivingDao receivingDao;

    //注入UserDao
    @Autowired
    private UserDao userDao;

    @Test
    public void testInsertReceiving() throws Exception{
        User user = userDao.queryUserById(1);
        Receiving receiving = new Receiving("jack1", "12345678910", "123456", "广东省珠海市金湾区广东科学技术职业学院", user);
        int insert = receivingDao.insertReceiving(receiving);
        System.out.println("insert = " + insert);
    }

    @Test
    public void testQueryReceivingByUserId() throws Exception{
        List<Receiving> receivingList = receivingDao.queryReceivingByUserId(1);
        System.out.println(receivingList);
    }

    @Test
    public void testSetIsDefault() throws Exception{
        int set = receivingDao.setIsDefault(2,1);
        System.out.println("update = " + set);
    }

    @Test
    public void testDeleteReceiving() throws Exception{
        int delete = receivingDao.deleteReceiving(1);
        System.out.println("delete = " + delete);
    }

    @Test
    public void testQueryDefaultReceiving() throws Exception{
        Receiving receiving = receivingDao.queryDefaultReceiving(1);
        System.out.println(receiving);
    }

}
