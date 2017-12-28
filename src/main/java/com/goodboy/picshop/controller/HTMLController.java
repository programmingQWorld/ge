package com.goodboy.picshop.controller;

import com.goodboy.picshop.dto.UserDto;
import com.goodboy.picshop.exception.LinkErrorException;
import com.goodboy.picshop.exception.LinkExpiredException;
import com.goodboy.picshop.service.ResetPwdService;
import com.goodboy.picshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;

/**
 * 处理HTML跳转的控制器
 */
@Controller
public class HTMLController {

    @Autowired
    private ResetPwdService resetPwdService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/detail-*.html", method = RequestMethod.GET)
    public String commodityDetail(){
        return "detail";
    }

    @RequestMapping(value = "/author-*.html", method = RequestMethod.GET)
    public String author(){
        return "author";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/user/emailActive", method = RequestMethod.GET)
    public String active(@RequestParam("checkCode") String checkCode, Model model){
        try {
            List list = resetPwdService.parseEmailLink(checkCode).getList();
            userService.emailActive((String)list.get(0));
        }catch (LinkExpiredException lee){
            model.addAttribute("errorMsg", "链接过期");
            return "error";
        }catch (LinkErrorException e){
            model.addAttribute("errorMsg", "链接错误");
            return "error";
        }
        return "active";
    }

    @RequestMapping(value = "/user/resetPwd", method = RequestMethod.GET)
    public String reset(@RequestParam("checkCode") String checkCode, Model model){
        try {
            UserDto userDto = resetPwdService.parsePwdLink(checkCode);
            model.addAttribute("account", userDto.getList().get(0));
            model.addAttribute("email", userDto.getList().get(1));
        }catch (LinkExpiredException lee){
            model.addAttribute("errorMsg", "链接过期");
            return "error";
        }catch (LinkErrorException e){
            model.addAttribute("errorMsg", "链接错误");
            return "error";
        }
        return "resetPassword";
    }
}
