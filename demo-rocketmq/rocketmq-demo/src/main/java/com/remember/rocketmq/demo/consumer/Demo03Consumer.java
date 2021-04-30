package com.remember.rocketmq.demo.consumer;

import com.remember.rocketmq.demo.message.Demo03Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author wangjihao
 * @date 2020/12/2
 */
@Slf4j
@Component
@RocketMQMessageListener(
        topic = Demo03Message.TOPIC,
        consumerGroup = "demo03-consumer-group-" + Demo03Message.TOPIC
)
public class Demo03Consumer implements RocketMQListener<Demo03Message> {


    @Override
    public void onMessage(Demo03Message message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
