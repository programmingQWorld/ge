package com.goodboy.picshop.controller;

import com.goodboy.picshop.dto.CommodityGetDto;
import com.goodboy.picshop.dto.JSONResult;
import com.goodboy.picshop.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 注解为REST控制器，所有方法返回json数据
 */
@RestController
@RequestMapping("/commodity")
public class CommodityController {
    //注入依赖
    @Autowired
    private CommodityService commodityService;

    //查询所有商品
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONResult<CommodityGetDto> getAll(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                              @RequestParam(value = "limit", defaultValue = "4") int limit){
        CommodityGetDto commodityGetDto = commodityService.getAll(offset, limit);
        return new JSONResult<CommodityGetDto>(true, commodityGetDto);
    }

    //查询商品详情
    @RequestMapping(value = "/{commodityId}/detail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONResult<CommodityGetDto> getById(@PathVariable("commodityId") int commodityId){
        CommodityGetDto commodityGetDto = commodityService.getById(commodityId);
        return new JSONResult<CommodityGetDto>(true, commodityGetDto);
    }
}
