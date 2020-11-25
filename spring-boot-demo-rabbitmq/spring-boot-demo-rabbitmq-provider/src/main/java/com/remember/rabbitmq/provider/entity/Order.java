package com.remember.rabbitmq.provider.entity;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
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
@Validated
public class Order implements Serializable {


    private static final long serialVersionUID = 3155702992463925456L;

    @NotNull
    @Max(value = 10, message = "这个错误信息")
    @Min(3)
    private String orderId;

    @NotNull
    private String goodsName;

    @NotNull
    private String detail;

    @PositiveOrZero
    private BigDecimal price;

    @Positive
    private Integer num;

    private BigDecimal totalMoney;

    private String remake;


}
