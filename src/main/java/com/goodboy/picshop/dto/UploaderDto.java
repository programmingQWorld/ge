package com.goodboy.picshop.dto;

/**
 * 封装上传的执行结果
 */
public class UploaderDto extends BaseDto {
    private String filePath;        //保存文件的路径
    private String fileName;        //保存的文件名

    public UploaderDto() {
    }

    public UploaderDto(StatusEnum statusEnum, String filePath, String fileName) {
        this.setStatus(statusEnum.getStatus());
        this.setInfo(statusEnum.getInfo());
        this.filePath = filePath;
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
