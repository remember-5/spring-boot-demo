package com.remember.rocketmq.demo.producer;

import com.remember.rocketmq.demo.message.Demo03Message;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 定时消息
 * @author wangjihao
 * @date 2020/12/2
 */
@Component
public class Demo03Producer {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public SendResult syncSendDelay(Integer id, int delayLevel) {
        Message<Demo03Message> message = MessageBuilder.withPayload(new Demo03Message(id)).build();
        return rocketMQTemplate.syncSend(Demo03Message.TOPIC, message, 30 * 1000, delayLevel);
    }

    public void asyncSendDelay(Integer id, int delayLevel, SendCallback callback) {
        Message<Demo03Message> message = MessageBuilder.withPayload(new Demo03Message(id)).build();
        rocketMQTemplate.asyncSend(Demo03Message.TOPIC, message, callback, 30 * 1000, delayLevel);
    }


}
