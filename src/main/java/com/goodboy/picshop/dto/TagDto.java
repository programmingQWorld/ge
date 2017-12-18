package com.goodboy.picshop.dto;

import com.goodboy.picshop.entity.Tag;

import java.util.List;

public class TagDto extends BaseDto {
    private List<Tag> tagList;

    public TagDto(StatusEnum statusEnum) {
        this.setStatus(statusEnum.getStatus());
        this.setInfo(statusEnum.getInfo());
    }

    public TagDto(StatusEnum statusEnum, List<Tag> tagList) {
        this.setStatus(statusEnum.getStatus());
        this.setInfo(statusEnum.getInfo());
        this.tagList = tagList;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    @Override
    public String toString() {
        return "TagDto{" +
                "status = " + this.getStatus() +
                ",info = " + this.getInfo() +
                ",tagList = " + tagList +
                '}';
    }
}
