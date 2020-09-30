package com.remember.validation.controller;

import com.alibaba.fastjson.JSONObject;
import com.remember.validation.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangjiahao
 * @date 2020/4/27
 */
@Slf4j
@Validated
@RestController
@RequestMapping("test")
public class TestController {


    @GetMapping
    public JSONObject test1(@NotNull String id) {
        log.info("id = {}", id);
        JSONObject result = new JSONObject();
        result.put("code", 200);
        result.put("message", "success");
        result.put("data", "noting");
        return result;
    }

    @PostMapping
    public JSONObject test2(@Valid User user) {
        log.info("user = {}", user);
        JSONObject result = new JSONObject();
        result.put("code", 200);
        result.put("message", "success");
        result.put("data", "noting");
        return result;
    }

    @PostMapping("getArray")
    public JSONObject getArray(List<String> list) {
        log.info("list = {}", list);
        JSONObject result = new JSONObject();
        result.put("code", 200);
        result.put("message", "success");
        result.put("data", "noting");
        return result;
    }

    @PostMapping("getArray")
    public JSONObject getArray(String ids) {
        List<String> split = Arrays.asList(ids.split(","));
        log.info("list = {}", split);
        JSONObject result = new JSONObject();
        result.put("code", 200);
        result.put("message", "success");
        result.put("data", "noting");
        return result;
    }

}
