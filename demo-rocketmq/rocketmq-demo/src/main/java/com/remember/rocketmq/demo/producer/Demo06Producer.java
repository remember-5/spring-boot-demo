package com.remember.rocketmq.demo.producer;

import com.remember.rocketmq.demo.message.Demo06Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
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
public class Demo06Producer {
    private final RocketMQTemplate rocketMQTemplate;

    public SendResult syncSendOrderly(Integer id) {
        Demo06Message message = new Demo06Message(id);
        return rocketMQTemplate.syncSendOrderly(Demo06Message.TOPIC, message, String.valueOf(id));
    }


    public void asyncSendOrderly(Integer id, SendCallback callback) {
        Demo06Message message = new Demo06Message(id);
        rocketMQTemplate.asyncSendOrderly(Demo06Message.TOPIC, message, String.valueOf(id), callback);
    }

    public void onewaySendOrderly(Integer id) {
        Demo06Message message = new Demo06Message(id);
        rocketMQTemplate.sendOneWayOrderly(Demo06Message.TOPIC, message, String.valueOf(id));


    }


}
