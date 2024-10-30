package com.fish.marketgoods.service;

import com.fish.marketgoods.pojo.entity.ShopCar;
import com.fish.marketgoods.pojo.vo.ShopCarVo;

import java.util.List;

public interface ShopCarService {


    List<ShopCarVo>  selectShopCarByUserId(int i);

    void deleteGoodsByGoodsId(Integer id);

    void   cleanShopCar();

    void   UpdataForCountBygetShopCarId(ShopCar a);

    boolean addGoodsToCar(ShopCar shopCar);

  /*  ShopCar selectGoodsByUserId(Integer userId);*/
}
