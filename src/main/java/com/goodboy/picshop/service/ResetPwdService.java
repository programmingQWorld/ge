package com.goodboy.picshop.service;

import com.goodboy.picshop.dto.UserDto;

import java.util.List;

public interface ResetPwdService {
    /**
     * 解密邮箱激活链接
     * @param checkCode
     * @return 获取一个几个对象：邮箱+链接失效时间
     */
    UserDto parseEmailLink(String checkCode);
    /**
     * 解密密码重置链接
     * @param checkCode
     * @return 获取一个几个对象：昵称+邮箱+链接失效时间
     */
    UserDto parsePwdLink(String checkCode);

}
