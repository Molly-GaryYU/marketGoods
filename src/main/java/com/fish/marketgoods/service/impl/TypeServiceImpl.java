package com.fish.marketgoods.service.impl;

import com.fish.marketgoods.dao.GoodsTypeDao;
import com.fish.marketgoods.pojo.entity.GoodsType;
import com.fish.marketgoods.pojo.vo.GoodsTypeVo;
import com.fish.marketgoods.service.TypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service /*交给SpringIoc容器处理，这样才能自动装配*/
public class TypeServiceImpl implements TypeService {
    @Resource
    GoodsTypeDao goodsTypeDao;

    @Override
    public boolean addGoodsType(GoodsType goodsType) {
        int i=goodsTypeDao.insert(goodsType);
        if (i==1){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public List<GoodsTypeVo> selectTypeByParentsId(int i) {
        List<GoodsTypeVo>  parentTypes= goodsTypeDao.selectTypeByParentsId(i);
        //这个语句的作用是首先判断parentTypes是GoodsTypeVo的子对象，
        //是的话就会自动遍历parentTypes中的每一个元素
        //for循环中使用查询语句会非常的影响性能
        //因为设计数据库的时候不够完善导致（有待改善）
        for (GoodsTypeVo  g: parentTypes){
            //‘’的中只能有一个数字或字母表示字符 ""表示字符串
            //所以字符可以直接转换成字符串。字符串需要使用charAt（n) 来获取第几个字符。
            //‘’是char类型 ，“”是String
            String d=g.getGoodsTypeId()+"1";
            int id=Integer.parseInt(d);
           /* System.err.println(id);*/
            List<GoodsTypeVo>  childrenTypes= goodsTypeDao.selectTypeByParentsId(id);
            g.setChildrenType(childrenTypes);
         /*   System.err.println(childrenTypes);*/
        }
 /*   for (GoodsTypeVo  g: parentTypes){
            List<GoodsTypeVo> childrenTypes=goodsTypeDao.selectTypeByParentsId(g.getParentType());
            g.setChildrenType(childrenTypes);
        }
        List<GoodsTypeVo> childrenTypes  =goodsTypeDao.selectTypeByParentsIds(parentTypes);
        //使用流式处理收集
        childrenTypes.stream().collect(Collectors.groupingBy(GoodsTypeVo ::getParentType));*/
        return parentTypes;
    }

    @Override
    public List<GoodsType> selectAllTypes() {

        return goodsTypeDao.selectAllType();
    }

    @Override
    public boolean updateGoodsType(GoodsType goodsType) {
        if (goodsTypeDao.updateByPrimaryKeySelective(goodsType)!=0){
            return true;
        }else {
            return false;
        }

    }

    @Override
    public boolean deleteGoodsType(Integer goodsTypeId) {
        if ( goodsTypeDao.deleteByPrimaryKey(goodsTypeId)!=0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<GoodsType> searchGoodsType(String typeName) {
        return    goodsTypeDao.searchGoodsType(typeName);
    }
}
