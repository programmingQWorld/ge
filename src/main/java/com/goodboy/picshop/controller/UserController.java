package com.goodboy.picshop.controller;


import com.goodboy.picshop.dto.*;
import com.goodboy.picshop.entity.User;
import com.goodboy.picshop.exception.*;
import com.goodboy.picshop.service.*;
import com.goodboy.picshop.util.GenerateLinkUtils;
import com.goodboy.picshop.util.md5Password;
import org.apache.ibatis.annotations.Param;
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
// 跨域请求
@CrossOrigin
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
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private CartService cartService;

    UserDto userDto=null;
    //登录验证
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONResult<UserDto> login(@RequestParam("account") String account,@RequestParam("password") String password,HttpSession session){

        String pwd= md5Password.md5Password(password);
        try{
            userDto=userService.userLogin(account,pwd);

            session.setAttribute("user",userDto.getUser());

            CartDto dbCartDto =  cartService.getCartInfoByUserId(userDto.getUser().getId());
            CartDto sessionCartDto = (CartDto) session.getAttribute("usercart");

            if (sessionCartDto != null && dbCartDto != null ) {
                if (sessionCartDto.getItems() != null && sessionCartDto.getItems().size() > 0) {
                    // 合并
                    for ( CartItemDto dto : sessionCartDto.getItems() ) {
                        if ( !dbCartDto.getItems().contains(dto)  ) {
                            dbCartDto.getItems().add(dto);
                        }
                    }
                }
            }
            CartDto dto = (dbCartDto == null) ? sessionCartDto : dbCartDto ;
            session.setAttribute("usercart", dto);

        }catch (UserErrorException uie){
            userDto=new UserDto(StatusEnum.USER_ERROR);
        }
        return  new JSONResult<UserDto>(true,userDto);
    }
    //用户注销
    @RequestMapping("/logout")
    public JSONResult<UserDto> logout(HttpServletRequest request){
        // 保存信息回到数据库
        CartDto cartDto = (CartDto) request.getSession().getAttribute("usercart");
        User userOnline = (User) request.getSession().getAttribute("user");
        cartService.saveUserCartInfo( userOnline.getId(), cartDto  );
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
    public JSONResult<UserDto> insert(@RequestParam("account") String account, @RequestParam("password") String password,@RequestParam("email") String email,@RequestParam("phone") String phone){

        try {
            String pwd= md5Password.md5Password(password);
            userDto = userService.insert(account, pwd, email, phone);
        }
        catch (DuplicateKeyException e){
            userDto=new UserDto(StatusEnum.REPEAT_USER);
        }
        return new JSONResult<UserDto>(true, userDto);
    }

    // 上传头像
    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONResult<UploaderDto> upload(@RequestParam("file") MultipartFile file){
        UploaderDto uploaderDto = null;
        try {
            uploaderDto = uploaderService.upload(file, "/upload/avatar/");
        }catch (NotAllowFileTypeException nafte){   // 不允许上传的文件类型
            uploaderDto = new UploaderDto(StatusEnum.NOT_ALLOW_FILE_TYPE);
        }catch (FileTooLargeException ftle){        // 文件过大
            uploaderDto = new UploaderDto(StatusEnum.FILE_TOO_LARGE);
        }catch (UnknownException ue){               // 未知错误
            uploaderDto = new UploaderDto(StatusEnum.UNKNOWN_ERROR);
        }
        return new JSONResult<UploaderDto>(true, uploaderDto);
    }

    //更新信息
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONResult<UserDto> update(@RequestParam("nickname")String nickname, @RequestParam("filePath") String filePath, @RequestParam("sex")String sex, @RequestParam("birthday") @DateTimeFormat(pattern = "yyyy-MM-dd")Date birthday, @RequestParam("email") String email, HttpSession session){
        User user=(User)session.getAttribute("user");
        if(user==null){
            return new JSONResult<UserDto>(false,"用户未登录");
        }
        user.setNickname(nickname);
        user.setSex(sex.charAt(0));
        user.setAvatar(filePath);
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

    // 根据用户id查询用户上架的商品
    @RequestMapping(value = "/{userId}/commodity", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONResult<CommodityDto> getCommodity(@PathVariable("userId") int userId,
                                                 @RequestParam(value = "offset", defaultValue = "0") int offset,
                                                 @RequestParam(value = "limit", defaultValue = "8") int limit){
        CommodityDto commodityDto = null;
        try{
            commodityDto = commodityService.getByUser(userId, offset, limit);
        }catch (NoCommodityFoundException ncfe){
            commodityDto = new CommodityDto(StatusEnum.NO_COMMODITY_FOUND);
        }
        return new JSONResult<CommodityDto>(true, commodityDto);
    }

    // 查询用户上架的商品
    @RequestMapping(value = "{userId}/commodity/count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONResult<CommodityDto> countCommodity(@PathVariable("userId") int userId){
        CommodityDto commodityDto = commodityService.countByUserId(userId);
        return new JSONResult<CommodityDto>(true, commodityDto);
    }

    // 新增商品
    @RequestMapping(value = "/commodity/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONResult<CommodityDto> addCommodity(@RequestParam("cname") String name,
                                                 @RequestParam("cpic") String picture,
                                                 @RequestParam("tagId") int tagId,
                                                 @RequestParam("csizeWidth") float sizeWidth,
                                                 @RequestParam("csizeHeight") float sizeHeight,
                                                 @RequestParam("cprice") float price,
                                                 HttpSession session){
        User user=(User)session.getAttribute("user");
        if(user==null){
            return new JSONResult<CommodityDto>(false,"用户未登录");
        }
        CommodityDto commodityDto = null;
        try {
            commodityDto = commodityService.add(name, picture, tagId, sizeWidth, sizeHeight, price, user.getId());
        }catch (NoUserException nue){       // 用户不存在
            commodityDto = new CommodityDto(StatusEnum.NO_USER);
        }catch (CommodityRepeatException cre){      // 商品重复
            commodityDto = new CommodityDto(StatusEnum.COMMODITY_REPEAT);
        }
        return new JSONResult<CommodityDto>(true, commodityDto);
    }

    // 是否登录
    @RequestMapping(value = "/islogin", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONResult<UserDto> isLogin(HttpSession session){
        User user=(User)session.getAttribute("user");
        if(user==null){
            return new JSONResult<UserDto>(false,"用户未登录");
        }
        return new JSONResult<UserDto>(true, new UserDto(StatusEnum.SUCCESS, user));
    }
}
