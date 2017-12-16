package com.goodboy.picshop.dao;

import com.goodboy.picshop.BaseTest;
import com.goodboy.picshop.dto.CartDto;
import com.goodboy.picshop.dto.CartItemDto;
import com.goodboy.picshop.entity.Cart;
import com.goodboy.picshop.entity.CartItem;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;

/**
 * CartDao的测试类
 */
public class CartDaoTest extends BaseTest{

    //注入CartDao
    @Autowired
    private CartDao cartDao;
    @Autowired
    private CartItemDao cartItemDao;

    @Test
    public void testInsertCart() throws Exception{
        int insert = cartDao.insertCart(3, 1);
        System.out.println("insert = " + insert);
    }

    /*
    @Test
    public void testQueryCartByUserId() throws Exception{
        List<Cart> cartList = cartDao.queryCartByUserId(1);
        System.out.println(cartList);
    }*/

    @Test
    public void testDeleteCart() throws Exception{
        int delete = cartDao.deleteCart(1);
        System.out.println("delete = " + delete);
    }


    /**
     * test convert cart to DTO
     */
    @Test
    public void testGetCart() {
        Cart cart =  cartDao.queryCartByUserId(3);
        System.out.println(cart);
        List<CartItem> list = cartItemDao.queryCartItemsByCartId(cart.getId());
        CartDto dto = cart.toDto();
        System.out.println(dto);
    }

    @Test
    public void testA () {
        List<Integer> l = null;
        for (Integer i : l) {  // 爆出空指针异常
            System.out.println(i);
        }
    }

    @Test
    public void testSaveUserCartInfo() throws Exception {
        int value = cartDao.saveUserCartInfo(5);
        System.out.println("-->添加完成");
    }

}
