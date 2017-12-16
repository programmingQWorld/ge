package com.goodboy.picshop.dao;

import com.goodboy.picshop.BaseTest;
import com.goodboy.picshop.dto.CartItemDto;
import com.goodboy.picshop.entity.Commodity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartItemDaoTest extends BaseTest {
    @Autowired
    private CartItemDao cartItemDao;

    @Test
    public void testAddItemList () {
        // 数据准备
        Map<String, Object> map = new HashMap<String, Object>();
        // 为用户 张起灵(2) 的购物车添加商品
        map.put("cartid", 2);

        List<CartItemDto> items = new ArrayList<>();
        //int[] cids = new int[] {12, 13, 14};
        int[] cids = new int[] {1,2,3,4,5};
        for (int i=0; i<cids.length; i++ ) {

            int id = cids[i];
            CartItemDto dto = new CartItemDto();
            dto.setCommid(id);
            items.add( dto );

        }
        map.put("itemList", items);
        // 调用
        cartItemDao.saveCartItemList(map);
    }
}
