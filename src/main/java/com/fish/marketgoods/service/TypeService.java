package com.fish.marketgoods.service;

import com.fish.marketgoods.pojo.entity.GoodsType;
import com.fish.marketgoods.pojo.vo.GoodsTypeVo;

import java.util.List;


/*    快速创建接口实现类alt+enter 选着implement*/
public interface TypeService {

    /**
     * 增添商品功能
     * @param goodsType
     * @return
     */

    boolean addGoodsType(GoodsType goodsType);

    /**
     * 通过查询一级id返回所有的一级类型返回前端
     * @return
     */

    List<GoodsTypeVo> selectTypeByParentsId(int i);

    List<GoodsType> selectAllTypes();

    boolean  updateGoodsType(GoodsType goodsType);

    boolean  deleteGoodsType(Integer goodsTypeId);

    List<GoodsType>  searchGoodsType(String typeName);
}
