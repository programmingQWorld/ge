package com.goodboy.picshop.dao;

import com.goodboy.picshop.BaseTest;
import com.goodboy.picshop.entity.Commodity;
import com.goodboy.picshop.entity.Order;
import com.goodboy.picshop.entity.Receiving;
import com.goodboy.picshop.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * OrderDao的测试类
 */
public class OrderDaoTest extends BaseTest {

    //注入OrderDao
    @Autowired
    private OrderDao orderDao;

    //注入CommodityDao
    @Autowired
    private CommodityDao commodityDao;

    //注入ReceivingDao
    @Autowired
    private ReceivingDao receivingDao;

    //注入UserDao
    @Autowired
    private UserDao userDao;

    @Test
    public void testInsertOrder() throws Exception{
        User user = userDao.queryUserById(1);
        Receiving receiving = receivingDao.queryDefaultReceiving(1);
        Commodity commodity = commodityDao.queryCommodityById(1);
        Order order = new Order("No000002", new Date(), user, receiving, commodity);
        int insert = orderDao.insertOrder(order);
        System.out.println("insert = " + insert);
    }

    @Test
    public void testQueryOrderById() throws Exception{
        Order order = orderDao.queryOrderById(1);
        System.out.println(order);
    }

    @Test
    public void testQueryByUserId() throws Exception{
        List<Order> orderList = orderDao.queryOrderByUserId(1, 0, 5);
        System.out.println(orderList);
    }

    @Test
    public void testQueryOrderByUserIdAndIsPay() throws Exception{
        List<Order> orderList = orderDao.queryOrderByUserIdAndIsPay(1, 0, 0, 5);
        System.out.println(orderList);
    }
}
