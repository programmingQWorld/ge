package com.goodboy.picshop.service;

import com.goodboy.picshop.dto.UserDto;
import com.goodboy.picshop.entity.User;
import org.springframework.mail.SimpleMailMessage;

/**
 * 用户模块接口
 */
public interface UserService {

    /**
     * 通过账号（account）和密码查询用户id（登录）
     * @param account 用户账号
     * @param password 用户密码
     * @return 用户id
     */
    UserDto userLogin(String account,String password);
    /**
     * 通过用户id查询单个用户实体
     * @param id
     * @return 单个用户实体
     */
    UserDto queryUserById(int id);
    /**
     * 用户注册
     * @param account
     * @param password
     * @param email
     * @param phone
     * @return
     */
    UserDto insert(String account, String password, String email, String phone);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    UserDto update(User user);
    /**
     * 通过用户email查询单个用户实体,主要用于忘记密码操作
     * @param email
     * @return 单个用户实体
     */
    UserDto findUserByEmail(String email);


    /**
     * 发送邮件
     * @param mail
     * @return
     */
    UserDto sendEmail(SimpleMailMessage mail);

    /**
     * 修改密码
     * @param password
     * @param email
     * @return 影响行数
     */
    UserDto updatePwd(String password,String email);

}
