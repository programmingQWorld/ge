package com.goodboy.picshop.service;

import com.goodboy.picshop.dto.UserDto;

import java.util.List;

public interface ResetPwdService {
    /**
     * 通过传入加密链接将其解密
     * @param checkCode
     * @return 获取一个几个对象：昵称+邮箱+链接失效时间
     */
    UserDto parseLink(String checkCode);

}
