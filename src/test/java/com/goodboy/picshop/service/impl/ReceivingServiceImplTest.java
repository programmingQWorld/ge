package com.goodboy.picshop.service.impl;


import com.goodboy.picshop.BaseTest;
import com.goodboy.picshop.dto.ReceivingDto;
import com.goodboy.picshop.dto.UserDto;
import com.goodboy.picshop.entity.Receiving;
import com.goodboy.picshop.entity.User;
import com.goodboy.picshop.service.ReceivingService;
import com.goodboy.picshop.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class ReceivingServiceImplTest extends BaseTest{

    @Autowired
    private ReceivingService receivingService;
    @Autowired
    private UserService userService;

    @Test
    public void testInsertReceiving() throws Exception{
        UserDto userDto = userService.queryUserById(1);
        User user=userDto.getUser();
        Receiving receiving=new Receiving("jack3", "12345678910", "123456", "广东省珠海市金湾区广东科学技术职业学院", user);
        ReceivingDto receivingDto=receivingService.insertReceiving(receiving);
        System.out.println(receivingDto);
    }
    @Test
    public void testDeleteReceiving() throws Exception{
        ReceivingDto receivingDto=receivingService.delete(6);
        System.out.println(receivingDto);
    }
    @Test
    public void testQueryReceivingByUserId() throws Exception{
        ReceivingDto receivingDto=receivingService.queryReceivingByUserId(1);
        System.out.println(receivingDto);
    }
    @Test
    public void testQueryDefaultReceiving() throws Exception{
        ReceivingDto receivingDto=receivingService.queryDefaultReceiving(1);
        System.out.println(receivingDto);
    }
}
