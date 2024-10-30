package com.fish.marketgoods.dao;

import com.fish.marketgoods.pojo.entity.UserOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserOrderDao {
    int deleteByPrimaryKey(String orderId);

    int insert(UserOrder record);

    int insertSelective(UserOrder record);

    UserOrder selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(UserOrder record);

    int updateByPrimaryKey(UserOrder record);

    List<UserOrder>  selectUserOrderByUserId(Integer userId);

    List<UserOrder>  getAllOrders();

    List<UserOrder> searchInfoBYTypeName(String searchInfo);
}