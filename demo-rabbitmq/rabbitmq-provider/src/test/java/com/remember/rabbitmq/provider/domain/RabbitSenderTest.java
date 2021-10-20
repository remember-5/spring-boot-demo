package com.remember.rabbitmq.provider.domain;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.remember.rabbitmq.provider.SpringBootDemoRabbitmqProviderApplicationTests;
import com.remember.rabbitmq.provider.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
class RabbitSenderTest extends SpringBootDemoRabbitmqProviderApplicationTests {

    @Autowired
    private RabbitSender rabbitSender;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @Test
    public void testSender1() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("number", "12345");
        properties.put("sendTime", simpleDateFormat.format(new Date()));
        rabbitSender.send("Hello RabbitMQ For Spring Boot!", properties);
    }


    @Test
    public void testSender2() {
        Order order = new Order();
        order.setOrderId("D001111");
        order.setGoodsName("第一个订单");
        order.setDetail("测试第一个订单");
        rabbitSender.sendOrder(order);
    }


    @Test
    public void testSender3() {
        for (int i = 0; i < 4; i++) {
            Order order = new Order();
            order.setOrderId("D00" + i);
            order.setGoodsName("第" + i + "个订单");
            order.setDetail("测试" + i + "第一个订单");
            String jsonString = JSONObject.toJSONString(order,
                    SerializerFeature.WriteNullStringAsEmpty,
                    SerializerFeature.WriteNullNumberAsZero);
            log.info("{}", jsonString);
            rabbitSender.send(jsonString, null);
        }

    }


}
