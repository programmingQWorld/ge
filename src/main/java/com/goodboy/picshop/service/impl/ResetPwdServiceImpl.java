package com.goodboy.picshop.service.impl;

import com.goodboy.picshop.dto.StatusEnum;
import com.goodboy.picshop.dto.UserDto;
import com.goodboy.picshop.exception.LinkErrorException;
import com.goodboy.picshop.exception.LinkExpiredException;
import com.goodboy.picshop.service.ResetPwdService;
import com.goodboy.picshop.util.GenerateLinkUtils;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ResetPwdServiceImpl implements ResetPwdService {
    @Override
    public UserDto parseEmailLink(String checkCode) {
        List list= null;
        try {
            list = GenerateLinkUtils.parseEmailLink(checkCode);
            long  endTimes = Long.parseLong((String)list.get(1));//链接失效时间
            long curTime = System.currentTimeMillis();//获取当前时间
            if(curTime>=endTimes){
                throw new LinkExpiredException("link expired");
            }
        } catch (LinkExpiredException lee) {
            throw lee;
        }catch (Exception e) {
            throw new LinkErrorException("link error");
        }
        return new UserDto(StatusEnum.SUCCESS,list);
    }


    //解析重新设置密码链接
    public UserDto parsePwdLink(String checkCode) {
        List list= null;
        try {
            list = GenerateLinkUtils.toSetPayrollPwd2(checkCode);
            long  endTimes = Long.parseLong((String)list.get(2));//链接失效时间
            long curTime = System.currentTimeMillis();//获取当前时间
            if(curTime>=endTimes){
                throw new LinkExpiredException("link expired");
            }
        } catch (LinkExpiredException lee) {
            throw lee;
        }catch (Exception e) {
            throw new LinkErrorException("link error");
        }
        return new UserDto(StatusEnum.SUCCESS,list);
    }
}
