package com.remember.jwt.controller;

import com.remember.jwt.utils.JwtUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author wangjiahao
 * @date 2020/5/28
 */
@RequestMapping("test")
@RestController
public class TestController {


    @GetMapping
    public String test() {
        String crmId = getCrmId();
        return "请求成功";
    }

    @GetMapping("auth")
    public String auth() {
        // TODO 解密

        // TODO 通过设备号获取客户号

        // setRedis
        return JwtUtils.createJWT("123", "13361928119", 1000L * 60 * 60);
    }

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }


    public static String getCrmId() {
        String token = getHttpServletRequest().getHeader("Authorization").substring(7);
        String id = JwtUtils.parseJWT(token).getId();
        //TODO 根据标识查询redis 如 API:AUTH:JWT:KEY

        // String crmId = redis.getString(key);
        // System.err.println(crmId);
        return "";
    }
}
