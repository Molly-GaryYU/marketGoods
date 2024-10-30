package com.fish.marketgoods.pojo.vo;

import com.fish.marketgoods.pojo.entity.GoodsType;
import lombok.Data;

import java.util.List;

@Data
public class GoodsTypeVo extends GoodsType {
    /**
     * 装子类商品集合
     */
    private List<GoodsTypeVo> childrenType;
}
