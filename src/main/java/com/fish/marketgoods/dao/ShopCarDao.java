package com.fish.marketgoods.dao;

import com.fish.marketgoods.pojo.entity.ShopCar;
import com.fish.marketgoods.pojo.vo.ShopCarVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopCarDao {
    int deleteByPrimaryKey(Integer shopCarId);

    int insert(ShopCar record);

    int insertSelective(ShopCar record);

    ShopCar selectByPrimaryKey(Integer shopCarId);

    int updateByPrimaryKeySelective(ShopCar record);

    int updateByPrimaryKey(ShopCar record);

    List<ShopCarVo> selectGoodsByUserId(int i);

    void cleanShopCar();


}