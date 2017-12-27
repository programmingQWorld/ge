package com.goodboy.picshop.service.impl;

import com.goodboy.picshop.dao.UserDao;
import com.goodboy.picshop.dto.*;
import com.goodboy.picshop.entity.User;
import com.goodboy.picshop.exception.*;
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

    public UserDto userLogin(String account, String password,String checkCode,String code) {
        User user=userDao.queryUserIdByAccountAndPassword(account,password);
        try{
            if(user==null){
                throw new UserErrorException("password error");}
            if(user.getActive()==0){
                throw new UserNoActiveException("user no active");}
            if(!checkCode.equals(code)){
                throw new CheckCodeErrorException("check code error");}
            }
            catch (UserErrorException uie){
                  throw  uie;
        }
            catch (UserNoActiveException unace){
                 throw  unace;
         }
            catch (CheckCodeErrorException ccee){
            throw  ccee;
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


    public UserDto emailActive(String email) {
        userDao.emailActive(email);
        return new UserDto(StatusEnum.SUCCESS);
    }


    public UserDto getCheckCode() {
        String code = "";
        char [] random = new char[]{'0', '1','2', '3','4', '5','6', '7','8', '9','A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'}; //随机数
        for(int i = 0; i < 4; i++) { //循环操作
            int charIndex = (int)Math.floor(Math.random() * 36); //取得随机数的索引
            code += random[charIndex]; //根据索引取得随机数加到code上
        }
        return new UserDto(StatusEnum.SUCCESS,code);
    }

    public UserDto insert(String account, String password, String email, String phone) {
        try {
            User user = new User(account, password, email, phone);
            if (userDao.searchAccount(account) != 0) {
                throw new AccountRegisterException("account is already exist");
            }
            if (userDao.searchEmail(email) != 0) {
                throw new EmailRegisterException("email is already exist");
            }
            userDao.insertUser(user);
        }
        catch (AccountRegisterException are){
            throw  are;
        }
            catch (EmailRegisterException ere){
            throw  ere;
        }

           return new UserDto(StatusEnum.SUCCESS);
    }
}
