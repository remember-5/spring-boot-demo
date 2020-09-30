package com.remember.swagger.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.remember.swagger.enetity.Result;
import com.remember.swagger.enetity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.IOException;

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
        log.info("这是logstash开始打印的日志");
        log.info("我就是不想测试");
        log.info("日志到底是个啥");
        log.info("让我们看看接下来会发生什么");
        log.debug("这是debug");
        log.debug("这是debug啊");
        log.debug("这是debug吗");
        log.warn("这是warn吗");
        log.warn("这是warn吗");
        log.warn("这是warn吗");
        return new Result(200, "success", null);
    }


    @PostMapping
    @ApiOperation(value = "这是一个post请求", notes = "这是post请求的描述，让我多写点字")
    public Result testPost(User user) {
        return new Result(200, "success", user);
    }

    @PostMapping("upload")
    @ApiOperation(value = "这是一个post请求", notes = "这是post请求的描述，让我多写点字")
    public Result testPost(@RequestParam(value = "file") MultipartFile file) {
        try {
            String decode = QrCodeUtil.decode(file.getInputStream());
            if (decode.length() != 19) {
                return new Result(500, " ", "");
            }
            return new Result(200, " ", decode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(500, " ", "");
    }


    @PostMapping("uploadBybase64")
    @ApiOperation(value = "这是一个post请求", notes = "这是post请求的描述，让我多写点字")
    public Result testPostByBase64(String str) {
        String s = UUID.fastUUID().toString();
        MultipartFile multipartFile = base64toMultipart(str, s + ".jpg");
        String iccid = null;
        try {
            iccid = QrCodeUtil.decode(multipartFile.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(200, " ", iccid);
    }


    public MultipartFile base64toMultipart(String data, String fileName) {
        try {
            String[] baseStrs = data.split(",");
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = decoder.decodeBuffer(baseStrs[1]);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            return new Base64MultipartFile(b, baseStrs[0], fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
