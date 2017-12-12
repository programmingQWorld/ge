package com.goodboy.picshop.service.impl;

import com.goodboy.picshop.dto.UploaderDto;
import com.goodboy.picshop.service.UploaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class UploaderServiceImpl implements UploaderService {

    public UploaderDto upload(MultipartFile file) {
        // 检查文件是否为空
        if(!file.isEmpty()){
            try {
                // 文件保存路径
                String filePath = System.getProperty("evan.webapp") + "/resources/upload/" + file.getOriginalFilename();
                // 转存文件
                file.transferTo(new File(filePath));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
}
