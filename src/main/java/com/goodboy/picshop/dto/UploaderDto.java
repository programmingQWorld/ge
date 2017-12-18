package com.goodboy.picshop.dto;

/**
 * 封装上传的执行结果
 */
public class UploaderDto extends BaseDto {
    private String fileName;        //保存的文件名

    public UploaderDto() {
    }

    public UploaderDto(StatusEnum statusEnum) {
        this.setStatus(statusEnum.getStatus());
        this.setInfo(statusEnum.getInfo());
    }

    public UploaderDto(StatusEnum statusEnum, String fileName) {
        this.setStatus(statusEnum.getStatus());
        this.setInfo(statusEnum.getInfo());
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}

