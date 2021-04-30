package com.remember.swagger.enetity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @author wangjiahao
 * @date 2020/4/27
 */
@Data
@ToString
@ApiModel(value = "统一返回", description = "所有的返回将以此形式返回")
@AllArgsConstructor
public class Result {

    @ApiModelProperty(value = "状态码", required = true)
    private Integer code;

    @ApiModelProperty(value = "提示", required = true)
    private String message;

    @ApiModelProperty(value = "返回数据", required = true)
    private Object data;
}
