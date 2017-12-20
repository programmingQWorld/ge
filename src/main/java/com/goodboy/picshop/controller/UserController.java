package com.goodboy.picshop.controller;


import com.goodboy.picshop.dto.JSONResult;
import com.goodboy.picshop.dto.StatusEnum;
import com.goodboy.picshop.dto.UploaderDto;
import com.goodboy.picshop.dto.UserDto;
import com.goodboy.picshop.entity.User;
import com.goodboy.picshop.exception.*;
import com.goodboy.picshop.service.ResetPwdService;
import com.goodboy.picshop.service.UploaderService;
import com.goodboy.picshop.service.UserService;
import com.goodboy.picshop.util.GenerateLinkUtils;
import com.goodboy.picshop.util.md5Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


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
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private ResetPwdService resetPwdService;

    UserDto userDto=null;
    //登录验证
    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONResult<UserDto> login(String account,String password,HttpSession session){
        String pwd= md5Password.md5Password(password);
        try{
            userDto=userService.userLogin(account,pwd);
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
    @RequestMapping(value = "/insertUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONResult<UserDto> insert(String account, String password, String email, String phone){

        try {
            String pwd= md5Password.md5Password(password);
            userDto = userService.insert(account, pwd, email, phone);
        }
        catch (DuplicateKeyException e){
            userDto=new UserDto(StatusEnum.REPEAT_USER);
        }
        return new JSONResult<UserDto>(true, userDto);
    }

    //更新信息
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONResult<UserDto> update(@RequestParam("nickname")String nickname, @RequestParam("file") MultipartFile file, @RequestParam("sex")String sex, @DateTimeFormat(pattern = "yyyy-MM-dd")Date birthday, @RequestParam("email") String email, HttpSession session){
        UploaderDto uploaderDto=null;
        User user=(User)session.getAttribute("user");
        if(user==null){
            return new JSONResult<UserDto>(false,"用户未登录");
        }
        try {
            uploaderDto=uploaderService.upload(file,"/resources/upload/avatar/");
        }catch (UserNoLoginException unle){// 用户未登录
            userDto = new UserDto(StatusEnum.NO_LOGIN);
        }
        catch (NotAllowFileTypeException nafte){   // 不允许上传的文件类型
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
    //获取邮箱发送密码重置地址
    @RequestMapping(value = "/sendEmail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONResult<UserDto> findUser(String email) throws Exception{
        try{
            userDto=userService.findUserByEmail(email);
            SimpleMailMessage mail=new SimpleMailMessage();
            mail.setTo(userDto.getUser().getEmail());//收件人邮箱地址
            mail.setFrom("xxx@qq.com");//发件人
            mail.setSubject("密码重置");//主题
            mail.setText("要使用新的密码, 请使用以下链接启用密码,链接24小时后过期:" + GenerateLinkUtils.generateResetPwdLink(userDto.getUser()) );//正文
            mailSender.send(mail);

        }catch (NoEmailException uee){
            userDto=new UserDto(StatusEnum.NO_EMAILL);
        }

        return new JSONResult<UserDto>(true, userDto);
    }
    //用户点击密码重置链接解析链接并判断身份
    //判断链接是否失效
    //根据邮箱和昵称查找用户
    @RequestMapping(value = "/resetPwd", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONResult<UserDto> showForgetPwdUser(String checkCode) throws Exception{
        try {
            List list = resetPwdService.parseLink(checkCode).getList();
            userDto=userService.findUserByEmail((String)list.get(1));
        }catch (LinkErrorException lee){
            userDto=new UserDto(StatusEnum.LINK_ERROR);
         }catch (LinkExpiredException le){
            userDto=new UserDto(StatusEnum.LINK_EXPIRED);
        }catch (Exception e){
            userDto=new UserDto(StatusEnum.LINK_ERROR);
        }
        return new JSONResult<UserDto>(true, userDto);
    }

    //更新、重置用户密码
    @RequestMapping(value = "/updatePwd", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONResult<UserDto> updatePwd(String password,String email) throws Exception{
        String pwd=md5Password.md5Password(password);
        userDto=userService.updatePwd(pwd,email);
        return new JSONResult<UserDto>(true, userDto);
    }
}
