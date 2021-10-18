package com.remember.rocketmq.demo.producer;

import com.remember.rocketmq.demo.message.Demo05Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

/**
 * @author wangjihao
 * @date 2020/12/2
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class Demo05Producer {
    private final RocketMQTemplate rocketMQTemplate;


    public SendResult syncSend(Integer id) {
        // 创建 Demo05Message 消息
        Demo05Message message = new Demo05Message();
        message.setId(id);
        // 同步发送消息
        return rocketMQTemplate.syncSend(Demo05Message.TOPIC, message);
    }


}
