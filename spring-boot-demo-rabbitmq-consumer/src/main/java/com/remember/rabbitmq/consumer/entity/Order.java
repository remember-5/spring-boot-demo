package com.remember.rabbitmq.consumer.entity;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author wangjiahao
 * @date 2020/4/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Order implements Serializable {


    private static final long serialVersionUID = -8600575287901796872L;

    private String orderId;

    private String goodsName;

    private String detail;

    private BigDecimal price;

    private Integer num;

    private BigDecimal totalMoney;

    private String remake;


}
