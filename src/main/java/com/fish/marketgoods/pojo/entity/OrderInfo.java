package com.fish.marketgoods.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * order_info
 * @author 
 */
@Data
public class OrderInfo implements Serializable {
    private Integer orderInfoId;

    private String orderId;

    /**
     * 原来商品的链接
     */
    private Integer originGoodsId;

    /**
     * 当时下单的价格
     */
    private Long price;

    /**
     * 当时下单数量
     */
    private Integer count;

    /**
     * 当时商品的图片
     */
    private String img;

    /**
     * 下单时候的原价
     */
    private Double oldPrice;

    private static final long serialVersionUID = 1L;

    public Integer getOrderInfoId() {
        return orderInfoId;
    }

    public void setOrderInfoId(Integer orderInfoId) {
        this.orderInfoId = orderInfoId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getOriginGoodsId() {
        return originGoodsId;
    }

    public void setOriginGoodsId(Integer originGoodsId) {
        this.originGoodsId = originGoodsId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Double oldPrice) {
        this.oldPrice = oldPrice;
    }
}