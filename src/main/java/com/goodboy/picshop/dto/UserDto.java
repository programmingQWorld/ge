package com.goodboy.picshop.dto;

import com.goodboy.picshop.entity.User;

import java.util.List;

/**
 * 封装用户执行操作后的结果
 */

public class UserDto {

    private int status;     // 标识
    private String info;    // 标识信息
    private User user;      // 返回用户信息
    private int userId;         //返回用户id
    private List list;      //返回一个集合

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
    public UserDto(StatusEnum statusEnum,User user,int userId) {
        this.status = statusEnum.getStatus();
        this.info = statusEnum.getInfo();
        this.user=user;
        this.userId=userId;
    }
    public UserDto(StatusEnum statusEnum,List list) {
        this.status = statusEnum.getStatus();
        this.info = statusEnum.getInfo();
        this.list=list;
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
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public String toString() {
        return "UserDto{status = " + this.status + ", info = " + this.info + "}";
    }



}
