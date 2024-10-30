package com.fish.marketgoods.service;

import com.fish.marketgoods.pojo.entity.UserOrder;

import java.util.List;

public interface UserOrderService {

   List<UserOrder>   selectUserOrderByUserId(Integer userId);

    List<UserOrder>  getAllOrders();

    List<UserOrder>  searchUserOrder(String searchInfo);
}
