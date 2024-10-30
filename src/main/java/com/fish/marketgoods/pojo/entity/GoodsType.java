package com.fish.marketgoods.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * goods_type
 * @author 
 */
@Data
public class GoodsType implements Serializable {
    private Integer goodsTypeId;

    private Integer parentType;

    private String typeName;

    /**
     * 类型的序号，根据序号排序优先展示
     */
    private Integer sort;

    private String iconClass;

    private static final long serialVersionUID = 1L;

    public Integer getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(Integer goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public Integer getParentType() {
        return parentType;
    }

    public void setParentType(Integer parentType) {
        this.parentType = parentType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }
}