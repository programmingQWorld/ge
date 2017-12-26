package com.goodboy.picshop.service.impl;

import com.goodboy.picshop.dao.CommodityDao;
import com.goodboy.picshop.dao.UserDao;
import com.goodboy.picshop.dto.CommodityDto;
import com.goodboy.picshop.dto.StatusEnum;
import com.goodboy.picshop.entity.Commodity;
import com.goodboy.picshop.entity.User;
import com.goodboy.picshop.exception.NoCommodityFoundException;
import com.goodboy.picshop.exception.CommodityRepeatException;
import com.goodboy.picshop.exception.NoUserException;
import com.goodboy.picshop.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {
    //注入依赖
    @Autowired
    private CommodityDao commodityDao;

    @Autowired
    private UserDao userDao;

    public CommodityDto getByTag(int tagId, int offset, int limit) {
        // 根据标签id查询商品
        List<Commodity> commodityList = commodityDao.queryCommodityByTagId(tagId, offset, limit);
        try {
            // 判断是否有商品
            if (!commodityList.isEmpty()) {
                CommodityDto commodityDto = new CommodityDto(StatusEnum.SUCCESS, commodityList);
                return commodityDto;
            } else {    // 没有商品
                throw new NoCommodityFoundException("no commodity found");
            }
        }catch (NoCommodityFoundException ncfe){
            throw ncfe;
        }
    }

    public CommodityDto getAll(int offset, int limit) {
        // 查询所有商品
        List<Commodity> commodityList = commodityDao.queryAllCommodity(offset, limit);
        try {
            // 判断是否有商品
            if (!commodityList.isEmpty()) {
                CommodityDto commodityDto = new CommodityDto(StatusEnum.SUCCESS, commodityList);
                return commodityDto;
            } else {    // 没有商品
                throw new NoCommodityFoundException("no commodity found");
            }
        }catch (NoCommodityFoundException ncfe){
            throw ncfe;
        }
    }

    public CommodityDto getById(int commodityId) {
        // 根据id查询商品
        Commodity commodity = commodityDao.queryCommodityById(commodityId);
        try {
            // 判断商品是否存在
            if (commodity != null) {
                CommodityDto commodityDto = new CommodityDto(StatusEnum.SUCCESS, commodity);
                return commodityDto;
            }else {     // 商品不存在
                throw new NoCommodityFoundException("commodity not found");
            }
        }catch (NoCommodityFoundException ncfe){
            throw ncfe;
        }
    }

    public CommodityDto getByUser(int userId, int offset, int limit) {
        // 根据用户id查询商品
        List<Commodity> commodityList = commodityDao.queryCommodityByUserId(userId, offset, limit);
        try {
            // 判断是否有商品
            if(!commodityList.isEmpty()){
                CommodityDto commodityDto = new CommodityDto(StatusEnum.SUCCESS, commodityList);
                return commodityDto;
            }else {     // 没有商品
                throw new NoCommodityFoundException("no commodity found");
            }
        }catch (NoCommodityFoundException ncfe){
            throw ncfe;
        }
    }

    // 使用事务管理
    @Transactional
    public CommodityDto add(String name, String picture, int tagId, float sizeWidth, float sizeHeight, float price, int userId) {
        try{
            // 根据用户id获取用户实体
            User user = userDao.queryUserById(userId);
            if(user == null){   // 用户不存在
                throw new NoUserException("no user");
            }
            Commodity commodity = new Commodity(name, price, picture, 0, new Date(), sizeWidth, sizeHeight, user);
            // 执行增加商品操作
            int insert = commodityDao.insertCommodity(commodity);
            if(insert >= 1) {     // 增加商品成功
                // 执行增加商品标签操作
                insert = commodityDao.insertCommodityRelTag(tagId, commodity.getId());
                if(insert >= 1) {       // 增加商品标签成功
                    return new CommodityDto(StatusEnum.SUCCESS);
                }
            }
        }catch (NoUserException nue){
            throw nue;
        }catch (DuplicateKeyException dke){
            throw new CommodityRepeatException("repeat commodity");
        }
        return null;
    }

    public CommodityDto getByLevel(int level) {

        // 根据商品等级查询
        List<Commodity> commodityList = commodityDao.queryCommodityByLevel(level);
        try {
            // 判断是否有商品
            if (!commodityList.isEmpty()) {
                CommodityDto commodityDto = new CommodityDto(StatusEnum.SUCCESS, commodityList);
                return commodityDto;
            } else {    // 没有商品
                throw new NoCommodityFoundException("no commodity found");
            }
        }catch (NoCommodityFoundException ncfe){
            throw ncfe;
        }
    }

    public CommodityDto update(int id, String name, float price, float sizeWidth, float sizeHeight) {
        Commodity commodity = commodityDao.queryCommodityById(id);
        commodity.setName(name);
        commodity.setPrice(price);
        commodity.setSizeWidth(sizeWidth);
        commodity.setSizeHeight(sizeHeight);
        commodityDao.updateCommodity(commodity);
        return new CommodityDto(StatusEnum.SUCCESS);
    }

    public CommodityDto countByUserId(int userId) {
        int count = commodityDao.countCommodityByUserId(userId);
        return new CommodityDto(StatusEnum.SUCCESS, count);
    }
}
