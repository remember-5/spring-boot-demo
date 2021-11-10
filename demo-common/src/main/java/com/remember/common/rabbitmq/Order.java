package com.remember.common.rabbitmq;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author wangjiahao
 * @date 2020/4/26
 */
@Data
public class Order implements Serializable {

    private String orderId;

    private String goodsName;

    private String detail;

    private BigDecimal price;

    private Integer num;

    private BigDecimal totalMoney;

    private String remake;


}
