package com.fish.marketgoods.service.impl;

import com.fish.marketgoods.dao.UserOrderDao;
import com.fish.marketgoods.pojo.entity.UserOrder;
import com.fish.marketgoods.service.UserOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserOrderServiceImpl implements UserOrderService {
    @Resource
    UserOrderDao userOrderDao;

    @Override
    public List<UserOrder> selectUserOrderByUserId(Integer userId) {

        return userOrderDao.selectUserOrderByUserId(userId);
    }

    @Override
    public List<UserOrder> getAllOrders() {

        return userOrderDao.getAllOrders();
    }

    @Override
    public List<UserOrder> searchUserOrder(String searchInfo) {
        return userOrderDao.searchInfoBYTypeName(searchInfo);
    }
}
