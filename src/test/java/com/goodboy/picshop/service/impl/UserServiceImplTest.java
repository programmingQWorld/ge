package com.goodboy.picshop.service.impl;

import com.goodboy.picshop.BaseTest;
import com.goodboy.picshop.dto.UserDto;
import com.goodboy.picshop.entity.User;
import com.goodboy.picshop.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class UserServiceImplTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void testLoginUser()throws Exception{
        UserDto userDto=userService.userLogin("test0003","test0001");
        System.out.println(userDto);
    }
    @Test
    public void testGetUserById() throws Exception{
        UserDto userDto = userService.queryUserById(1);
        System.out.println(userDto);
    }
    @Test
    public void testInsertUser() throws Exception{
        System.out.println(userService.insert("test0001","test0003", "123@qq.com","1234"));
    }
    @Test
    public void testUpdateUser() throws Exception{
        UserDto userDto = userService.queryUserById(6);
        User user=userDto.getUser();
        user.setNickname("java1");
        user.setAvatar("http:xxx.xxx/xxx.jpg");
        user.setSex('男');
        user.setBirthday(new Date());
        user.setEmail("1234@qq.com");
        System.out.println(userService.update(user));
    }
}
