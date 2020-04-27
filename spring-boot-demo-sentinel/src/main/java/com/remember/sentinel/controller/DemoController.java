package com.remember.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangjiahao
 * @date 2020/4/7
 */
@RestController
@RequestMapping("demo")
public class DemoController {

    @GetMapping("echo")
    public String echo() {
        return "echo";
    }

    @GetMapping("test")
    public String test() {
        return "test";
    }

    @GetMapping("sleep")
    public String sleep() throws InterruptedException {
        Thread.sleep(100L);
        return "sleep";
    }

    @GetMapping("product_info")
    @SentinelResource("demo_product_info_hot")
    public String productInfo(Integer id) {
        return "商品编号：" + id;
    }

    @GetMapping("/annotations_demo")
    @SentinelResource(value = "annotations_demo_resource",
            blockHandler = "blockHandler",
            fallback = "fallback")
    public String annotationsDemo(@RequestParam(required = false) Integer id) throws InterruptedException {
        if (id == null) {
            throw new IllegalArgumentException("id 参数不允许为空");
        }
        return "success...";
    }

    /**
     * 处理函数，参数最后多一个 BlockException，其余与原函数一致.
     *
     * @param id 程序id
     * @param ex 时间
     * @return String
     */
    public String blockHandler(Integer id, BlockException ex) {
        return "block：" + ex.getClass().getSimpleName();
    }


    /**
     * Fallback 处理函数，函数签名与原函数一致或加一个 Throwable 类型的参数.
     *
     * @param id        程序id
     * @param throwable 异常
     * @return String
     */
    public String fallback(Integer id, Throwable throwable) {
        return "fallback：" + throwable.getMessage();
    }

}
