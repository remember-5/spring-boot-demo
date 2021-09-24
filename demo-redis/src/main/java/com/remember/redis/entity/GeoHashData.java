package com.remember.redis.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author wangjiahao
 * @date 2021/9/24
 */
@Data
@Builder
public class GeoHashData {

    private String keyWord;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private String member;

}
