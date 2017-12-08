package com.goodboy.picshop.controller;

import com.goodboy.picshop.dto.CommodityGetDto;
import com.goodboy.picshop.dto.JSONResult;
import com.goodboy.picshop.entity.Commodity;
import com.goodboy.picshop.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 注解为REST控制器，所有方法返回json数据
 */
@RestController
@RequestMapping("/tag")
public class TagController {
    //注入依赖
    @Autowired
    private CommodityService commodityService;

    // 根据标签id查商品
    @RequestMapping(value = "/{tagId}/commodity", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONResult<CommodityGetDto> getByTag(@PathVariable("tagId") int tagId,
                                                @RequestParam(value = "offset", defaultValue = "0") int offset,
                                                @RequestParam(value = "limit", defaultValue = "5") int limit){
        CommodityGetDto commodityGetDto = commodityService.getByTag(tagId, offset, limit);
        return new JSONResult<CommodityGetDto>(true, commodityGetDto);
    }
}
