package com.remember.swagger.controller;

import com.remember.swagger.enetity.Result;
import com.remember.swagger.enetity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangjiahao
 * @date 2020/4/27
 */
@Slf4j
@Api(tags = "测试接口")
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping
    @ApiOperation(value = "这是一个get请求", notes = "这是get请求的描述，让我多写点字")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", required = true, type = "String"),
            @ApiImplicitParam(name = "age", value = "年纪", required = true, type = "int"),
    })
    public Result test1(String name, Integer age) {
        log.info("name = {},age = {}", name, age);
        return new Result(200, "success", null);
    }


    @PostMapping
    @ApiOperation(value = "这是一个post请求", notes = "这是post请求的描述，让我多写点字")
    public Result testPost(User user) {
        return new Result(200, "success", user);
    }

    ;


}
