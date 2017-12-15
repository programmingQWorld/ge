package com.goodboy.picshop.dto;

    /**
     * 封装上传的执行结果
     */
    public class UploaderDto  {
        private String fileName;        //保存的文件名
        private int status;                 // 标识
        private String info;                // 标识信息

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
    }

