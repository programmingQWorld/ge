package com.goodboy.picshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 处理HTML跳转的控制器
 */
@Controller
public class HTMLController {
    @RequestMapping(value = "/detail-*.html", method = RequestMethod.GET)
    public String commodityDetail(){
        return "detail";
    }
}
