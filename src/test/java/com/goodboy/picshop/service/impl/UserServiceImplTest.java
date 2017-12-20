package com.goodboy.picshop.service.impl;

import com.goodboy.picshop.BaseTest;
import com.goodboy.picshop.dto.UserDto;
import com.goodboy.picshop.entity.User;
import com.goodboy.picshop.service.UserService;
import com.goodboy.picshop.util.GenerateLinkUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

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

    @Test
    public void testSendEmail() throws Exception{
        SimpleMailMessage mail=new SimpleMailMessage();
        mail.setTo("814334428@qq.com");//收件人邮箱地址
        mail.setFrom("774659399@qq.com");//收件人
        mail.setSubject("spring自带javamail发送的邮件");//主题
        mail.setText("hello this mail is from spring javaMail ");//正文
        userService.sendEmail(mail);
        System.out.print("成功发送");
    }
    @Test
    public void testLinkUtils() throws Exception{
        User user=userService.findUserByEmail("12@qq.com").getUser();
        System.out.println(GenerateLinkUtils.generateResetPwdLink(user));
    }
}
