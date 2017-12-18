package com.goodboy.picshop.dto;

import com.goodboy.picshop.entity.User;

/**
 * 封装用户执行操作（注册，更新）后的结果
 */

public class UserDto {

    private int status;     // 标识
    private String info;    // 标识信息
    private User user;      // 返回用户信息

    public UserDto(){
    }
    public UserDto(StatusEnum statusEnum) {
        this.status = statusEnum.getStatus();
        this.info = statusEnum.getInfo();
    }

    public UserDto(StatusEnum statusEnum,User user) {
        this.status = statusEnum.getStatus();
        this.info = statusEnum.getInfo();
        this.user=user;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String toString() {
        return "UserDto{status = " + this.status + ", info = " + this.info + "}";
    }


}