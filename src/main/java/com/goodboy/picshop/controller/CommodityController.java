package com.goodboy.picshop.controller;

import com.goodboy.picshop.dto.CommodityDto;
import com.goodboy.picshop.dto.JSONResult;
import com.goodboy.picshop.dto.StatusEnum;
import com.goodboy.picshop.dto.UploaderDto;
import com.goodboy.picshop.exception.FileTooLargeException;
import com.goodboy.picshop.exception.NoCommodityFoundException;
import com.goodboy.picshop.exception.NotAllowFileTypeException;
import com.goodboy.picshop.exception.UnknownException;
import com.goodboy.picshop.service.CommodityService;
import com.goodboy.picshop.service.UploaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 注解为REST控制器，所有方法返回json数据
 */
@RestController
@RequestMapping("/commodity")
public class CommodityController {
    // 注入依赖
    @Autowired
    private CommodityService commodityService;

    @Autowired
    private UploaderService uploaderService;

    // 查询所有商品
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONResult<CommodityDto> getAll(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                           @RequestParam(value = "limit", defaultValue = "4") int limit){
        // 根据情况实例化
        CommodityDto commodityDto = null;
        try {
            // 查询成功
            commodityDto = commodityService.getAll(offset, limit);
        }catch (NoCommodityFoundException ndfe){    // 没有商品
            commodityDto = new CommodityDto(StatusEnum.NO_COMMODITY_FOUND);
        }
        // 返回json结果
        return new JSONResult<CommodityDto>(true, commodityDto);
    }

    // 查询商品详情
    @RequestMapping(value = "/{commodityId}/detail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONResult<CommodityDto> getById(@PathVariable("commodityId") int commodityId){
        CommodityDto commodityDto = null;
        try {
            commodityDto = commodityService.getById(commodityId);
        }catch (NoCommodityFoundException ncfe){    // 没有找到商品
            commodityDto = new CommodityDto(StatusEnum.NO_COMMODITY_FOUND);
        }
        return new JSONResult<CommodityDto>(true, commodityDto);
    }

    // 上传商品图片
    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONResult<UploaderDto> upload(@RequestParam("file") MultipartFile file){
        UploaderDto uploaderDto = null;
        try {
            uploaderDto = uploaderService.upload(file, "/resources/upload/");
        }catch (NotAllowFileTypeException nafte){   // 不允许上传的文件类型
            uploaderDto = new UploaderDto(StatusEnum.NOT_ALLOW_FILE_TYPE);
        }catch (FileTooLargeException ftle){        // 文件过大
            uploaderDto = new UploaderDto(StatusEnum.FILE_TOO_LARGE);
        }catch (UnknownException ue){               // 未知错误
            uploaderDto = new UploaderDto(StatusEnum.UNKNOWN_ERROR);
        }
        return new JSONResult<UploaderDto>(true, uploaderDto);
    }
}
