package com.goodboy.picshop.service.impl;

import com.goodboy.picshop.dao.CommodityDao;
import com.goodboy.picshop.entity.Commodity;
import com.goodboy.picshop.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {
    //注入依赖
    @Autowired
    private CommodityDao commodityDao;

    public List<Commodity> getByTag(int tagId, int offset, int limit) {
        return commodityDao.queryCommodityByTagId(tagId, offset, limit);
    }

    public List<Commodity> getAll(int offset, int limit) {
        return commodityDao.queryAllCommodity(offset, limit);
    }

    public Commodity getById(int commodityId) {
        return commodityDao.queryCommodityById(commodityId);
    }
}
