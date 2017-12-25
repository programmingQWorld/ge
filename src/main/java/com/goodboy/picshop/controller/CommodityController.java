package com.goodboy.picshop.controller;

import com.goodboy.picshop.dto.*;
import com.goodboy.picshop.entity.User;
import com.goodboy.picshop.exception.*;
import com.goodboy.picshop.service.CommodityService;
import com.goodboy.picshop.service.TagService;
import com.goodboy.picshop.service.UploaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * 注解为REST控制器，所有方法返回json数据
 */
@RestController
// 跨域请求
@CrossOrigin
@RequestMapping("/commodity")
public class CommodityController {
    // 注入依赖
    @Autowired
    private CommodityService commodityService;

    @Autowired
    private UploaderService uploaderService;

    @Autowired
    private TagService tagService;

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
            uploaderDto = uploaderService.upload(file, "/upload/");
        }catch (NotAllowFileTypeException nafte){   // 不允许上传的文件类型
            uploaderDto = new UploaderDto(StatusEnum.NOT_ALLOW_FILE_TYPE);
        }catch (FileTooLargeException ftle){        // 文件过大
            uploaderDto = new UploaderDto(StatusEnum.FILE_TOO_LARGE);
        }catch (UnknownException ue){               // 未知错误
            uploaderDto = new UploaderDto(StatusEnum.UNKNOWN_ERROR);
        }
        return new JSONResult<UploaderDto>(true, uploaderDto);
    }

    // 获取商品的标签
    @RequestMapping(value = "/{commodityId}/tag", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONResult<TagDto> getByCommodityId(@PathVariable("commodityId") int commodityId){
        TagDto tagDto = null;
        try{
            tagDto = tagService.getByCommodityId(commodityId);
        }catch (NoTagFoundException ntfe){
            tagDto = new TagDto(StatusEnum.NO_TAG_FOUND);
        }
        return new JSONResult<TagDto>(true, tagDto);
    }

    // 根据等级获取商品
    @RequestMapping(value = "/{level}/level", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONResult<CommodityDto> getByLevel(@PathVariable("level") int level){
        CommodityDto commodityDto = null;
        try {
            commodityDto = commodityService.getByLevel(level);
        }catch (NoCommodityFoundException ncfe){
            commodityDto = new CommodityDto(StatusEnum.NO_COMMODITY_FOUND);
        }
        return new JSONResult<CommodityDto>(true, commodityDto);
    }

    // 修改商品
    @RequestMapping(value = "/{commodityId}/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONResult<CommodityDto> update(@PathVariable("commodityId") int commodityId,
                                           @RequestParam("name") String name,
                                           @RequestParam("sizeW") float sizeWidth,
                                           @RequestParam("sizeH") float sizeHeight,
                                           @RequestParam("price") float price,
                                           HttpSession session){
        User user=(User)session.getAttribute("user");
        if(user==null){
            return new JSONResult<CommodityDto>(false,"用户未登录");
        }
        CommodityDto commodityDto = null;
        commodityDto = commodityService.update(commodityId, name, price, sizeWidth, sizeHeight);
        return new JSONResult<CommodityDto>(true, commodityDto);
    }
}
