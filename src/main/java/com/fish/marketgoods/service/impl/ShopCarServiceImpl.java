package com.fish.marketgoods.service.impl;

import com.fish.marketgoods.dao.GoodsDao;
import com.fish.marketgoods.dao.ShopCarDao;
import com.fish.marketgoods.pojo.entity.Goods;
import com.fish.marketgoods.pojo.entity.ShopCar;
import com.fish.marketgoods.pojo.vo.ShopCarVo;
import com.fish.marketgoods.service.ShopCarService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShopCarServiceImpl implements ShopCarService {
    @Resource
    ShopCarDao shopCarDao;
    @Resource
    GoodsDao goodsDao;

    @Override
    public List<ShopCarVo> selectShopCarByUserId(int i) {
         List<ShopCarVo> shopCar=  shopCarDao.selectGoodsByUserId(i);
       for ( ShopCarVo  s : shopCar ){
           int goodsNum=s.getGoodsId() ;
           Goods goodsDetail=goodsDao.selectByPrimaryKey(goodsNum);
           s.setGoods(goodsDetail);
       }
        return shopCar;
    }

    @Override
    public void cleanShopCar() {
        shopCarDao.cleanShopCar();
    }

    @Override
    public void UpdataForCountBygetShopCarId(ShopCar a) {
        shopCarDao.updateByPrimaryKeySelective(a);
    }

    @Override
    public boolean addGoodsToCar(ShopCar shopCar) {
        if (shopCar.getUserId()==0){
             System.err.println("false");
            return false;
        }else {
            shopCarDao.insert(shopCar);
            System.err.println("true");
            return true;
        }

    }

 /*   @Override
    public ShopCar selectGoodsByUserId(Integer goodsId) {

      return   shopCarDao.selectGoodsByUserInfo(goodsId);
    }*/

    @Override
    public void deleteGoodsByGoodsId(Integer id) {
        shopCarDao.deleteByPrimaryKey(id);

    }


}
