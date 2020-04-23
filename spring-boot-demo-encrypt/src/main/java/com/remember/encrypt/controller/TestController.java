package com.remember.encrypt.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.remember.encrypt.annotation.decrypt.RSADecryptBody;
import com.remember.encrypt.annotation.encrypt.RSAEncryptBody;
import com.remember.encrypt.eneity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangjiahao
 * @date 2020/4/23
 */
@Slf4j
@RestController
@RSAEncryptBody()
@RSADecryptBody
public class TestController {

    @GetMapping("encrypt")
    public JSONObject encrypt(){
        Person build = new Person("王家豪",24,"浦东新区");
        return JSONObject.parseObject(JSON.toJSONString(build));
    }

    @PostMapping("decrypt")
    public String decrypt(@RequestBody Person person){
        log.info("{}",person);
        return "success";
    }

}
