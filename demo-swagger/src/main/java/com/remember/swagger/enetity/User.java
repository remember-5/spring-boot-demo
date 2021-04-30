package com.remember.swagger.enetity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author wangjiahao
 * @date 2020/4/27
 */
@Data
@ToString
@ApiModel(value = "用户信息", description = "请填写用户信息")
@AllArgsConstructor
public class User {

    @ApiModelProperty(value = "名称", required = true)
    private String name;

    @ApiModelProperty(value = "年龄", required = true)
    private Integer age;

    @ApiModelProperty(value = "日期")
    private Date date;


}
