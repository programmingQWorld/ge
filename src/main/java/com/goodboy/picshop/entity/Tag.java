package com.goodboy.picshop.entity;

public class Tag {
    private int id;             //标签id
    private String name;        //标签名称

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tag{id = " + this.id + ", name = " + this.name + "}";
    }
}
