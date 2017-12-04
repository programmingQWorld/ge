package com.goodboy.picshop.dao;

import com.goodboy.picshop.BaseTest;
import com.goodboy.picshop.entity.Cart;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * CartDao的测试类
 */
public class CartDaoTest extends BaseTest{

    //注入CartDao
    @Autowired
    CartDao cartDao;

    @Test
    public void testInsertCart() throws Exception{
        int insert = cartDao.insertCart(3, 1);
        System.out.println("insert = " + insert);
    }

    @Test
    public void testQueryCartByUserId() throws Exception{
        List<Cart> cartList = cartDao.queryCartByUserId(1);
        System.out.println(cartList);
    }

    @Test
    public void testDeleteCart() throws Exception{
        int delete = cartDao.deleteCart(1);
        System.out.println("delete = " + delete);
    }
}
