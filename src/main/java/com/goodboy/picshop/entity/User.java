package com.goodboy.picshop.entity;

import java.util.Date;

/**
 * 用户实体类
 */
public class User {
    private int id;             //用户id
    private String account;     //用户账号
    private String password;    //用户密码
    private String nickname;    //用户昵称
    private String avatar;      //用户头像
    private char sex;            //用户性别
    private Date birthday;      //用户生日
    private String email;       //用户邮箱
    private String phone;       //用户手机

    public User() {
    }

    public User(int id, String account, String password, String nickname, String avatar, char sex, Date birthday, String email, String phone) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.nickname = nickname;
        this.avatar = avatar;
        this.sex = sex;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
    }

    //注册业务的构造器
    public User(String account, String password, String email, String phone) {
        this.account = account;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{ id = " + this.id
                + ", account = " + this.account
                + ", password = " + this.password
                + ", nickname = " + this.nickname
                + ", avatar = " + this.avatar
                + ", sex = " + this.sex
                + ", birthday = " + this.birthday
                + ", email = " + this.email
                + ", phone = " + this.phone + " }";
    }
}
