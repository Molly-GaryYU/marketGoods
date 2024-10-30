package com.fish.marketgoods.pojo.vo;

import com.fish.marketgoods.pojo.entity.Goods;
import com.fish.marketgoods.pojo.entity.ShopCar;

import lombok.Data;

@Data
public class UserAndGoods {
  public ShopCar shopCar;
    public String   userName ;
    public Goods  goods;
    public  Integer userId;
    public String url;
    public  Boolean addOrNot;
}
