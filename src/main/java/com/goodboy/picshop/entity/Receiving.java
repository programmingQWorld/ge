package com.goodboy.picshop.entity;

/**
 * 收货信息实体类
 */
public class Receiving {
    private int id;                 //收货信息id
    private String receiver;        //收货人
    private String phone;           //收货手机
    private String zipCode;         //邮政编码
    private String address;         //收货地址
    private int isDefault;          //是否默认地址
    private User user;              //所属用户实体，多对一复合属性

    public Receiving() {
    }

    public Receiving(String receiver, String phone, String zipCode, String address, User user) {
        this.receiver = receiver;
        this.phone = phone;
        this.zipCode = zipCode;
        this.address = address;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Receiving{ id = " + this.id
                + ", receiver = " + this.receiver
                + ", phone = " + this.phone
                + ", zipCode = " + this.zipCode
                + ", address = " + this.address
                + ", isDefault = " + this.isDefault
                + ", user = " + this.user + " }";
    }
}
