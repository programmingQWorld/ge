package com.goodboy.picshop.dao;

import com.goodboy.picshop.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

    /**
     * 通过账号（account）和密码查询用户id（登录）
     * @param account 用户账号
     * @param password 用户密码
     * @return 用户id
     */
    User queryUserIdByAccountAndPassword(@Param("account") String account, @Param("password") String password);

    /**
     * 更新用户个人信息
     * @param user 用户实体
     * @return 影响行数
     */
    int updateUser(User user);

    /**
     * 插入新用户记录（注册）
     * @param user 新用户实体
     * @return 插入的行数
     */
    int insertUser(User user);

    /**
     * 通过用户id查询单个用户实体
     * @param id 用户id
     * @return 单个用户实体
     */
    User queryUserById(int id);

    /**
     * 通过用户email查询单个用户实体,主要用于忘记密码操作
     * @param email
     * @return 单个用户实体
     */

    User findUserByEmail(String email);
}
