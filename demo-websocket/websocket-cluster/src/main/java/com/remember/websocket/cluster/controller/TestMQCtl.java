package com.remember.websocket.cluster.controller;

import com.alibaba.fastjson.JSON;
import com.remember.websocket.cluster.domain.RequestMessage;
import com.remember.websocket.cluster.service.IRedisSessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author fly
 * @description
 * @date 2021/12/14 16:13
 */
@Slf4j
@Controller
@RequestMapping(value = "/ws")
public class TestMQCtl {

    // 实现spring websocket需要引入AmqpTemplate类
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private IRedisSessionService redisSessionService;


    /**
     * 模拟登录
     * @param request /
     * @param name /
     * @return /
     */
    @RequestMapping(value = "loginIn", method = RequestMethod.GET)
    public String login(HttpServletRequest request, String name){
        HttpSession httpSession = request.getSession();
        // 如果登录成功，则保存到会话中
        httpSession.setAttribute("loginName", name);
        return "OK";
    }

    /**
     * 向执行用户发送请求
     * @param msg
     * @param name
     * @return
     */
    @RequestMapping(value = "send2user")
    @ResponseBody
    public int sendMq2User(String msg, String name){
        // 根据用户名称获取用户对应的session id值
        String wsSessionId = redisSessionService.get(name);
        RequestMessage demoMQ = new RequestMessage();
        demoMQ.setName(msg);

        // 生成路由键值，生成规则如下: websocket订阅的目的地 + "-user" + websocket的sessionId值。生成值类似:
        String routingKey = getTopicRoutingKey("demo", wsSessionId);
        // 向amq.topi交换机发送消息，路由键为routingKey
        log.info("向用户[{}]sessionId=[{}]，发送消息[{}]，路由键[{}]", name, wsSessionId, wsSessionId, routingKey);
        amqpTemplate.convertAndSend("amq.topic", routingKey,  JSON.toJSONString(demoMQ));
        return 0;
    }

    /**
     * 获取Topic的生成的路由键
     *
     * @param actualDestination
     * @param sessionId
     * @return
     */
    private String getTopicRoutingKey(String actualDestination, String sessionId){
        return actualDestination + "-user" + sessionId;
    }

}
