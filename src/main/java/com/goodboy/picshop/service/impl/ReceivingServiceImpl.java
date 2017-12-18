package com.goodboy.picshop.service.impl;

import com.goodboy.picshop.dao.ReceivingDao;
import com.goodboy.picshop.dao.UserDao;
import com.goodboy.picshop.dto.ReceivingDto;
import com.goodboy.picshop.dto.StatusEnum;
import com.goodboy.picshop.entity.Receiving;
import com.goodboy.picshop.service.ReceivingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceivingServiceImpl implements ReceivingService{

    @Autowired
    private ReceivingDao receivingDao;

    public ReceivingDto insertReceiving(Receiving receiving) {
        receivingDao.insertReceiving(receiving);
        return new ReceivingDto(StatusEnum.SUCCESS);
    }

    public ReceivingDto delete(int id) {
        receivingDao.deleteReceiving(id);
        return new ReceivingDto(StatusEnum.SUCCESS);
    }

    public ReceivingDto queryReceivingByUserId(int userId) {
        List<Receiving> receivingList=receivingDao.queryReceivingByUserId(userId);
        return new ReceivingDto(StatusEnum.SUCCESS,receivingList);
    }

    public ReceivingDto queryDefaultReceiving(int userId) {
        Receiving receiving=receivingDao.queryDefaultReceiving(userId);
        return new ReceivingDto(StatusEnum.SUCCESS,receiving);
    }

    public ReceivingDto setIsDefault(int id, int isDefault) {
        receivingDao.setIsDefault(id,isDefault);
        return  new ReceivingDto(StatusEnum.SUCCESS);
    }
}