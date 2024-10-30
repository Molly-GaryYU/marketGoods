package com.fish.marketgoods.pojo.vo;

import com.fish.marketgoods.pojo.entity.Goods;
import com.fish.marketgoods.pojo.entity.ShopCar;
import lombok.Data;

@Data
public class ShopCarVo extends ShopCar {
    private Goods goods;




}
