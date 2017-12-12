package com.goodboy.picshop.service;

import com.goodboy.picshop.dto.UploaderDto;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传业务接口
 */
public interface UploaderService {
    /**
     * 上传文件
     * @param file 接收上传文件的对象
     * @return 上传后的数据传输对象
     */
    UploaderDto upload(MultipartFile file);
}
