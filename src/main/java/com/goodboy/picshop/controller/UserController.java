package com.goodboy.picshop.controller;


import com.goodboy.picshop.dto.JSONResult;
import com.goodboy.picshop.dto.StatusEnum;
import com.goodboy.picshop.dto.UploaderDto;
import com.goodboy.picshop.dto.UserDto;
import com.goodboy.picshop.entity.User;
import com.goodboy.picshop.exception.FileTooLargeException;
import com.goodboy.picshop.exception.NotAllowFileTypeException;
import com.goodboy.picshop.exception.UnknownException;
import com.goodboy.picshop.exception.UserErrorException;
import com.goodboy.picshop.service.UploaderService;
import com.goodboy.picshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;


/**
 * 注解为REST控制器，所有方法返回json数据
 */
@RestController
@RequestMapping("/user")
public class UserController {
    //注入依赖
    @Autowired
    private UserService userService;
    @Autowired
    private UploaderService uploaderService;

    UserDto userDto=null;
    //登录验证
    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONResult<UserDto> login(String account,String password,HttpSession session){
        try{
            userDto=userService.userLogin(account,password);
        }catch (UserErrorException uie){
            userDto=new UserDto(StatusEnum.USER_ERROR);
        }

        session.setAttribute("user",userDto.getUser());
        return  new JSONResult<UserDto>(true,userDto);
    }
    //用户注销
    @RequestMapping("/logout")
    public JSONResult<UserDto> logout(HttpServletRequest request){
        request.getSession().invalidate();
        return  new JSONResult<UserDto>(true,new UserDto(StatusEnum.SUCCESS));
    }

    //用户信息
    @RequestMapping(value = "/queryUserById/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONResult<UserDto> queryUserById(@PathVariable int userId){
        userDto=userService.queryUserById(userId);
        return  new JSONResult<UserDto>(true,userDto);
    }

    //注册用户
    @RequestMapping(value = "/insert", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONResult<UserDto> insert(String account, String password, String email, String phone){

        try {
            userDto = userService.insert(account, password, email, phone);
        }
        catch (DuplicateKeyException e){
            userDto=new UserDto(StatusEnum.REPEAT_USER);
        }
        return new JSONResult<UserDto>(true, userDto);
    }

    //更新信息
    @RequestMapping(value = "/update", method = RequestMethod.POST/*, produces = MediaType.APPLICATION_JSON_UTF8_VALUE*/)
    public JSONResult<UserDto> update(@RequestParam("nickname")String nickname, @RequestParam("file") MultipartFile file, @RequestParam("sex")String sex, @DateTimeFormat(pattern = "yyyy-MM-dd")Date birthday, @RequestParam("email") String email, HttpSession session){
        User user=(User)session.getAttribute("user");

        UploaderDto uploaderDto=null;
        try {
            uploaderDto=uploaderService.upload(file,"/resources/upload/avatar/");
        }catch (NotAllowFileTypeException nafte){   // 不允许上传的文件类型
            userDto = new UserDto(StatusEnum.NOT_ALLOW_FILE_TYPE);
        }catch (FileTooLargeException ftle){        // 文件过大
            userDto = new UserDto(StatusEnum.FILE_TOO_LARGE);
        }catch (UnknownException ue){               // 未知错误
            userDto = new UserDto(StatusEnum.UNKNOWN_ERROR);
        }
        String avatar=uploaderDto.getFileName();


//        try {
//            byte[] bytes = sex.getBytes("ISO-8859-1");
//            String s=new String(bytes,"utf-8");
//
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        user.setNickname(nickname);
        user.setSex(sex.charAt(0));
        user.setAvatar(avatar);
        user.setBirthday(birthday);
        user.setEmail(email);
        userDto = userService.update(user);
        return new JSONResult<UserDto>(true, userDto);
    }
}
