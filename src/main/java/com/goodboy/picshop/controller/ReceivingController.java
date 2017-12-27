package com.goodboy.picshop.controller;


import com.goodboy.picshop.dto.JSONResult;
import com.goodboy.picshop.dto.ReceivingDto;
import com.goodboy.picshop.dto.StatusEnum;
import com.goodboy.picshop.entity.Receiving;
import com.goodboy.picshop.entity.User;
import com.goodboy.picshop.exception.UserNoLoginException;
import com.goodboy.picshop.service.ReceivingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/receiving")
public class ReceivingController {

    @Autowired
    private ReceivingService receivingService;

    //添加用户地址
    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONResult<ReceivingDto> insertReceiving(String receiver,String phone,String zipCode,String address,HttpSession session){
        User user=(User)session.getAttribute("user");
        if(user==null){
            return new JSONResult<ReceivingDto>(false,"用户未登录");
        }
        Receiving receiving=new Receiving(receiver,phone,zipCode,address,user);
        ReceivingDto receivingDto = receivingService.insertReceiving(receiving);
        return new JSONResult<ReceivingDto>(true, receivingDto);
    }

    //根据id删除用户地址
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONResult<ReceivingDto> deleteReceiving(@PathVariable int id){
        ReceivingDto receivingDto = receivingService.delete(id);
        return new JSONResult<ReceivingDto>(true, receivingDto);
    }

    //根据id查询用户地址
    @RequestMapping(value = "/searchReceiving", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONResult<ReceivingDto> queryReceivingByUserId(HttpSession session){
        User user=(User)session.getAttribute("user");
        if(user==null){
            return new JSONResult<ReceivingDto>(false,"用户未登录");
        }
        ReceivingDto receivingDto = receivingService.queryReceivingByUserId(user.getId());
        return new JSONResult<ReceivingDto>(true,receivingDto);
    }

    //查询用户默认地址
    @RequestMapping(value = "/searchDefaultReceiving", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONResult<ReceivingDto> queryDefaultReceiving(HttpSession session){
        User user=(User)session.getAttribute("user");
        if(user==null){
            return new JSONResult<ReceivingDto>(false,"用户未登录");
        }
        ReceivingDto receivingDto = receivingService.queryDefaultReceiving(user.getId());
        return new JSONResult<ReceivingDto>(true,receivingDto);
    }

    //设置用户默认地址
    @RequestMapping(value = "/setIsDefault/{receivingId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONResult<ReceivingDto> setIsDefault(@PathVariable int receivingId,HttpSession session){
        User user=(User)session.getAttribute("user");
        if(user==null){
            return new JSONResult<ReceivingDto>(false,"用户未登录");
        }

        Receiving defaultReceiving =receivingService.queryDefaultReceiving(user.getId()).getReceiving();//获取用户当前默认地址
        if(defaultReceiving!=null){
            receivingService.setIsDefault(defaultReceiving.getId(),0);//将原先默认地址设为非默认
        }

        ReceivingDto receivingDto =receivingService.setIsDefault(receivingId,1);//将地址设为默认地址
        return new JSONResult<ReceivingDto>(true,receivingDto);
    }
}
