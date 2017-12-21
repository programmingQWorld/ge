package com.goodboy.picshop.service.impl;

import com.goodboy.picshop.dao.UserDao;
import com.goodboy.picshop.dto.*;
import com.goodboy.picshop.entity.User;
import com.goodboy.picshop.exception.NoEmailException;
import com.goodboy.picshop.exception.UserErrorException;
import com.goodboy.picshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JavaMailSender mailSender;

    public UserDto userLogin(String account, String password) {
        User user=userDao.queryUserIdByAccountAndPassword(account,password);
        try{
            if(user==null)
                throw new UserErrorException("password error");
        }catch (UserErrorException uie){
            throw  uie;
        }
        return new UserDto(StatusEnum.SUCCESS,user,user.getId());
    }

    public UserDto queryUserById(int id) {
        User user=userDao.queryUserById(id);
        return new UserDto(StatusEnum.SUCCESS,user);
    }


    public UserDto update(User user) {
        userDao.updateUser(user);
        return new UserDto(StatusEnum.SUCCESS);
    }

    public UserDto findUserByEmail(String email) {
        User user=userDao.findUserByEmail(email);
        try{
            if(user==null)
                 throw new NoEmailException("no email");
        }catch (NoEmailException nee){
            throw  nee;
        }
        return new UserDto(StatusEnum.SUCCESS,user);
    }


    public UserDto sendEmail(SimpleMailMessage mail) {
        mailSender.send(mail);
        return new UserDto(StatusEnum.SUCCESS);
    }

    public UserDto updatePwd(String password, String email) {
        userDao.updatePwd(password,email);
        return new UserDto(StatusEnum.SUCCESS);
    }

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
