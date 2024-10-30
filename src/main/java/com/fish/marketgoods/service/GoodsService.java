package com.fish.marketgoods.service;

import com.fish.marketgoods.pojo.entity.Goods;

import java.util.List;

/*    快速创建接口实现类alt+enter 选着implement*/
public interface GoodsService {
    /**
     * 增添商品功能
     * @param goods
     * @return
     */

    boolean add(Goods goods);

    List<Goods> getGoodsByTypeId(Integer typeId);



    Goods getGoodsDetailByGoodsId(Integer goodsId);

    List<Goods>   getAllGoods();

   boolean updateGoodsInfo(Goods goods);

    List<Goods> searchGoodsByInfo(String searchInfo);


}
