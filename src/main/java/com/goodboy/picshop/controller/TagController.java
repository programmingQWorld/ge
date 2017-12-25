package com.goodboy.picshop.controller;

import com.goodboy.picshop.dto.CommodityDto;
import com.goodboy.picshop.dto.JSONResult;
import com.goodboy.picshop.dto.StatusEnum;
import com.goodboy.picshop.dto.TagDto;
import com.goodboy.picshop.exception.NoCommodityFoundException;
import com.goodboy.picshop.exception.NoTagFoundException;
import com.goodboy.picshop.service.CommodityService;
import com.goodboy.picshop.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 注解为REST控制器，所有方法返回json数据
 */
@RestController
// 跨域请求
@CrossOrigin
@RequestMapping("/tag")
public class TagController {
    // 注入依赖
    @Autowired
    private CommodityService commodityService;

    @Autowired
    private TagService tagService;

    // 根据标签id查商品
    @RequestMapping(value = "/{tagId}/commodity", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONResult<CommodityDto> getByTag(@PathVariable("tagId") int tagId,
                                             @RequestParam(value = "offset", defaultValue = "0") int offset,
                                             @RequestParam(value = "limit", defaultValue = "5") int limit){
        // 根据情况实例化
        CommodityDto commodityDto = null;
        try {
            // 查询成功
            commodityDto = commodityService.getByTag(tagId, offset, limit);
        }catch (NoCommodityFoundException ndfe){    // 没有商品
            commodityDto = new CommodityDto(StatusEnum.NO_COMMODITY_FOUND);
        }
        return new JSONResult<CommodityDto>(true, commodityDto);
    }

    // 查询所有标签
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONResult<TagDto> getAll(){
        TagDto tagDto = null;
        try {
            tagDto = tagService.getAll();
        }catch (NoTagFoundException e1){        // 没有标签
            tagDto = new TagDto(StatusEnum.NO_TAG_FOUND);
        }
        return new JSONResult<TagDto>(true, tagDto);
    }
}
