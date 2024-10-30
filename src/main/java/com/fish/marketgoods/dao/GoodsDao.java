package com.fish.marketgoods.dao;

import com.fish.marketgoods.pojo.entity.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper

public interface GoodsDao {
    int deleteByPrimaryKey(Integer goodsId);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer goodsId);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    List<Goods> getGoodsByTypeId(Integer typeId);

    List<Goods> getAllGoods();

    List<Goods>  searchGoodsByInfo(String searchInfo);
}