package com.goodboy.picshop.service.impl;

import com.goodboy.picshop.dao.CommodityDao;
import com.goodboy.picshop.dao.UserDao;
import com.goodboy.picshop.dto.CommodityAddDto;
import com.goodboy.picshop.dto.StatusEnum;
import com.goodboy.picshop.entity.Commodity;
import com.goodboy.picshop.entity.User;
import com.goodboy.picshop.exception.CommodityAddException;
import com.goodboy.picshop.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Commodity> getByTag(int tagId, int offset, int limit) {
        return commodityDao.queryCommodityByTagId(tagId, offset, limit);
    }

    public List<Commodity> getAll(int offset, int limit) {
        return commodityDao.queryAllCommodity(offset, limit);
    }

    public Commodity getById(int commodityId) {
        return commodityDao.queryCommodityById(commodityId);
    }

    public List<Commodity> getByUser(int userId, int offset, int limit) {
        return commodityDao.queryCommodityByUserId(userId, offset, limit);
    }

    /**
     * 增加商品，使用事务管理
     * @param name
     * @param picture
     * @param tagId
     * @param sizeWidth
     * @param sizeHeight
     * @param price
     * @return
     */
    @Transactional
    public CommodityAddDto add(String name, String picture, int tagId, float sizeWidth, float sizeHeight, float price) {
        try{
            int userId = 1;
            if(userId == 0){    // 是否已登录
                throw new CommodityAddException("no login");
            }
            User user = userDao.queryUserById(userId);
            Commodity commodity = new Commodity(name, price, picture, 0, new Date(), sizeWidth, sizeHeight, user);
            //执行增加商品操作
            int insert = commodityDao.insertCommodity(commodity);
            if(insert <= 0){    // 作品重复
                throw new CommodityAddException("repeat commodity");
            }else {     // 增加商品成功
                //执行增加商品标签操作
                insert = commodityDao.insertCommodityRelTag(tagId, commodity.getId());
                if(insert <= 0){
                    throw new CommodityAddException("repeat tag relation");
                }else {
                    return new CommodityAddDto(StatusEnum.SUCCESS);
                }
            }
        }catch (CommodityAddException cae) {
            throw cae;
        }
    }
}
