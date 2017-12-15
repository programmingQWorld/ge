package com.goodboy.picshop.service.impl;

import com.goodboy.picshop.dao.UserDao;
import com.goodboy.picshop.dto.*;
import com.goodboy.picshop.entity.User;
import com.goodboy.picshop.exception.UserErrorException;
import com.goodboy.picshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public UserDto userLogin(String account, String password) {
        User user=userDao.queryUserIdByAccountAndPassword(account,password);
        try{
            if(user==null)
                throw new UserErrorException("password error");
        }catch (UserErrorException uie){
            throw  uie;
        }
        return new UserDto(StatusEnum.SUCCESS,user);
    }

    public UserDto queryUserById(int id) {
        User user=userDao.queryUserById(id);
        return new UserDto(StatusEnum.SUCCESS,user);
    }


    public UserDto update(User user) {
        userDao.updateUser(user);
        return new UserDto(StatusEnum.SUCCESS);
    }
    @Transactional
    public UserDto insert(String account, String password, String email, String phone) {
       try{
           User user=new User(account,password,email,phone);
           userDao.insertUser(user);
           return new UserDto(StatusEnum.SUCCESS);
       }catch (DuplicateKeyException e){
           throw e;
       }

    }
}
