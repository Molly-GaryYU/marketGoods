package com.fish.marketgoods.dao;

import com.fish.marketgoods.pojo.entity.GoodsType;
import com.fish.marketgoods.pojo.vo.GoodsTypeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper

public interface GoodsTypeDao {
    int deleteByPrimaryKey(Integer goodsTypeId);

    int insert(GoodsType record);

    int insertSelective(GoodsType record);

    GoodsType selectByPrimaryKey(Integer goodsTypeId);

    int updateByPrimaryKeySelective(GoodsType record);

    int updateByPrimaryKey(GoodsType record);

    List<GoodsTypeVo> selectTypeByParentsId(int i);

    List<GoodsType> selectAllType( );

    List<GoodsTypeVo> selectTypeByParentsIds (@Param("ids") List parentTypes);

    List<GoodsType>  searchGoodsType(String typeName);
}