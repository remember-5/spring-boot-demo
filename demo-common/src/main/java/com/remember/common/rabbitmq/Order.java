package com.remember.common.rabbitmq;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author wangjiahao
 * @date 2020/4/26
 */
public class Order implements Serializable {

    private String orderId;

    private String goodsName;

    private String detail;

    private BigDecimal price;

    private Integer num;

    private BigDecimal totalMoney;

    private String remake;

    public Order(String orderId, String goodsName, String detail, BigDecimal price, Integer num, BigDecimal totalMoney, String remake) {
        this.orderId = orderId;
        this.goodsName = goodsName;
        this.detail = detail;
        this.price = price;
        this.num = num;
        this.totalMoney = totalMoney;
        this.remake = remake;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake;
    }
}
