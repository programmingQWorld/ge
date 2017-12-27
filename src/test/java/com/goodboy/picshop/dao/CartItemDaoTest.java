package com.goodboy.picshop.dao;

import com.goodboy.picshop.BaseTest;
import com.goodboy.picshop.dto.CartItemDto;
import com.goodboy.picshop.entity.Commodity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class CartItemDaoTest extends BaseTest {
    @Autowired
    private CartItemDao cartItemDao;

    @Test
    public void testAddItemList () {
        // 数据准备
        Map<String, Object> map = new HashMap<String, Object>();
        List<CartItemDto> items = new ArrayList<>();

        int[] cids = new int[] {11, 13, 14, 15};
        for ( int i = 0; i<cids.length; i++ ) {
            CartItemDto dto = new CartItemDto();
            dto.setCommid(cids[i]);
            items.add( dto );
        }
        map.put("items", cids);
        map.put("cartid", 2);         // 为用户 张起灵(2) 的购物车添加商品
        // 调用
        cartItemDao.saveCartItemList(map);
    }
    @Test
    public void testDelCartItems () {
        cartItemDao.clearCartItemsByCartid(2);
    }
    @Test
    public void  testTrailingZeros () {
        long sum = 0;
        long n = 666;
        long tmp = n / 5;
        while (tmp != 0) {
            sum += tmp;
            tmp = tmp / 5;
            System.out.println( "loop : " + tmp);
        }
        System.out.println(sum);
    }

    @Test
    public void testByteMax () {
        byte bv = 0;
        for (int i = 0; i < 200; i++) {
            System.out.println(bv ++);
        }
	    System.out.println( 5 / 2);
    }

    @Test
	public void testPower() {
	    double base = 13;
	    int exponent = 12;
	    double res = 1;
	    boolean flag = false;
	    if (exponent < 0) {
	    	exponent = 0 - exponent;
	    	flag = true;
	    }
	    while (exponent != 0) {
	    	if ((exponent & 1) == 1) {
	    		res = res * base;
		    }
		    base = base * base;
	    	exponent = exponent >>> 1;
	    }
	    if ( flag ) {
		    System.out.println(1 / res);
	    } else {
		    System.out.println( res );
	    }
    }
}
