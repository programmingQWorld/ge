package com.goodboy.picshop.controller;

import com.goodboy.picshop.dto.CommodityDto;
import com.goodboy.picshop.dto.JSONResult;
import com.goodboy.picshop.dto.UploaderDto;
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
        CommodityDto commodityDto = commodityService.getAll(offset, limit);
        return new JSONResult<CommodityDto>(true, commodityDto);
    }

    // 查询商品详情
    @RequestMapping(value = "/{commodityId}/detail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONResult<CommodityDto> getById(@PathVariable("commodityId") int commodityId){
        CommodityDto commodityDto = commodityService.getById(commodityId);
        return new JSONResult<CommodityDto>(true, commodityDto);
    }

    // 上传商品图片
    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONResult<UploaderDto> upload(@RequestParam("file") MultipartFile file){
        UploaderDto uploaderDto = uploaderService.upload(file);
        return new JSONResult<UploaderDto>(true, uploaderDto);
    }
}
