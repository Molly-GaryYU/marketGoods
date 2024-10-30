package com.fish.marketgoods.service.impl;

import com.fish.marketgoods.dao.GoodsDao;
import com.fish.marketgoods.pojo.entity.Goods;
import com.fish.marketgoods.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service //交给springIoc容器管理 后边就可以直接自动装配使用了
public class GoodsServiceImpl implements GoodsService {
    @Resource
    GoodsDao goodsDao;

    @Override
    public boolean add(Goods goods) {
       int i=goodsDao.insert(goods);

       if (i==1){
           return true;
       }else {
           return false;
       }
    }

    @Override
    public List<Goods>  getGoodsByTypeId(Integer typeId) {
         List<Goods> goods=  goodsDao.getGoodsByTypeId(typeId);
         return goods;
    }
//根据主键查询
    @Override
    public Goods getGoodsDetailByGoodsId(Integer goodsId) {
        return goodsDao.selectByPrimaryKey(goodsId);

    }

    @Override
    public List<Goods> getAllGoods() {

        return goodsDao.getAllGoods();
    }

    @Override
    public boolean updateGoodsInfo(Goods goods) {
       if (goodsDao.updateByPrimaryKeySelective(goods)==1){
           return true;
       }else {
           return false;
       }

    }

    @Override
    public List<Goods> searchGoodsByInfo( String searchInfo) {
        System.err.println(searchInfo);
        return goodsDao.searchGoodsByInfo(searchInfo);
    }


}
