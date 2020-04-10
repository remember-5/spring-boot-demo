package com.remember.rsa.controller;

import com.remember.rsa.annotation.Decrypt;
import com.remember.rsa.annotation.Encrypt;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangjiahao
 * @date 2020/3/23
 */
@RequestMapping("test")
@RestController
public class TestController {

    @GetMapping("api")
    public String test(String name, String age) {
        return name + age;
    }

    /**
     * 响应数据 加密
     */
    @GetMapping("sendResponseEncryData")
    @Encrypt
    @Decrypt
    public TestBean sendResponseEncryData() {
        TestBean testBean = new TestBean();
        testBean.setName("wangjiahao");
        testBean.setAge(18);
        return testBean;
    }

    /**
     * 获取 解密后的 请求参数
     */
    @PostMapping("getRequestData")
    @Encrypt
    @Decrypt
    public String getRequestData(@RequestBody TestBean testBean) {
        return testBean.toString();
    }

    @PostMapping("decryption")
    @Encrypt
    @Decrypt
    public String Decryption(@RequestBody TestBean testBean) {
        return testBean.toString();
    }

}
