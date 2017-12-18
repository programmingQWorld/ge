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

}
