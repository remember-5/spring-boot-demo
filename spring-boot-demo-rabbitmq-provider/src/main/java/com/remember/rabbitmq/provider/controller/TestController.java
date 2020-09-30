package com.remember.rabbitmq.provider.controller;

import com.remember.rabbitmq.provider.domain.RabbitSender;
import com.remember.rabbitmq.provider.entity.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangjiahao
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("test")
public class TestController {

    private final RabbitSender rabbitSender;

    @GetMapping("send")
    public String send() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Map<String, Object> properties = new HashMap<>(2);
        properties.put("number", "12345");
        properties.put("sendTime", simpleDateFormat.format(new Date()));
        rabbitSender.send("Hello RabbitMQ For Spring Boot!", properties);
        return "success";
    }


    @GetMapping("sendOrder")
    public String sendOrder() {
        Order order = new Order();
        order.setOrderId("D001111");
        order.setGoodsName("第一个订单");
        order.setDetail("测试第一个订单");
        rabbitSender.sendOrder(order);
        return "success";
    }


}
