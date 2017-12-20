package com.goodboy.picshop.service.impl;

import com.goodboy.picshop.dto.StatusEnum;
import com.goodboy.picshop.dto.UploaderDto;
import com.goodboy.picshop.exception.FileTooLargeException;
import com.goodboy.picshop.exception.NotAllowFileTypeException;
import com.goodboy.picshop.exception.UnknownException;
import com.goodboy.picshop.service.UploaderService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

@Service
public class UploaderServiceImpl implements UploaderService {

    public UploaderDto upload(MultipartFile file, String savePath) {
        // 检查文件是否为空
        if(!file.isEmpty()){
            try {
                // 判断文件的MIME类型及后缀名
                String fileContentType = file.getContentType();
                String filename = file.getOriginalFilename();
                String fileType = filename.substring(filename.lastIndexOf('.'));
                if((fileContentType.equals("image/jpeg") || fileContentType.equals("image/png")) && (fileType.equals(".jpg") || fileType.equals(".jpeg") || fileType.equals(".png"))) {
                    // 判断文件大小
                    if(file.getSize() <= 128000) {
                        // 文件名按时间戳规则重命名
                        filename = new Date().getTime() + fileType;
                        // 文件保存路径
                        String filePath = System.getProperty("evan.webapp") + savePath + filename;
                        // 转存文件
                        file.transferTo(new File(filePath));
                        return new UploaderDto(StatusEnum.SUCCESS, savePath + filename);
                    }else {     // 文件过大
                        throw new FileTooLargeException("file is too large");
                    }
                }else{      // 不允许上传的文件类型
                    throw new NotAllowFileTypeException("not allow file type");
                }
            }catch (NotAllowFileTypeException nafte){
                throw nafte;
            }catch (FileTooLargeException ftle){
                throw ftle;
            }catch (Exception e){
                throw new UnknownException("unknown exception");
            }
        }
        return new UploaderDto(StatusEnum.FAILURE);
    }
}
