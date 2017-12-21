package com.goodboy.picshop.dao;

import com.goodboy.picshop.BaseTest;
import com.goodboy.picshop.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * UserDao的测试类
 */
public class UserDaoTest extends BaseTest {

    //注入UserDao
    @Autowired
    private UserDao userDao;

    @Test
    public void testInsertUser() throws Exception{
        User user = new User("test0001", "test0001", "test0001@qq.com", "12345678910");
        int insert = userDao.insertUser(user);
        System.out.println("insert = " + insert);
    }

    @Test
    public void testQueryUserIdByAccountAndPassword() throws Exception{
        String account = "test0001";
        String password = "test0001";
        User user = userDao.queryUserIdByAccountAndPassword(account, password);
        System.out.println("userId = " + user.getId());
    }

    @Test
    public void testQueryUserById() throws Exception{
        int id = 1;
        User user = userDao.queryUserById(id);
        System.out.println(user);
    }

    @Test
    public void testUpdateUser() throws Exception{
        User user = userDao.queryUserById(1);
        user.setNickname("java");
        user.setAvatar("http:xxx.xxx/xxx.jpg");
        user.setSex('男');
        user.setBirthday(new Date());
        int update = userDao.updateUser(user);
        System.out.println("update = " + update);
        System.out.println(userDao.queryUserById(1));
    }
}
