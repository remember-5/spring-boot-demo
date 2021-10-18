package com.remember.rocketmq.demo.producer;

import com.remember.rocketmq.demo.message.Demo02Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 批量发送
 *
 * @author wangjihao
 * @date 2020/12/2
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class Demo02Producer {
    private final RocketMQTemplate rocketMQTemplate;

    public SendResult sendBatch(Collection<Integer> ids) {
        // <X> 创建多条 Demo02Message 消息
        ArrayList<Message> messages = new ArrayList<>(ids.size());

        for (Integer id : ids) {
            // 创建Demo02Message消息
            Demo02Message message = new Demo02Message();
            message.setId(id);
            // 构建 Spring Messaging 定义的 Message 消息
            messages.add(MessageBuilder.withPayload(message).build());
        }
        // 同步批量发送消息
        return rocketMQTemplate.syncSend(Demo02Message.TOPIC, messages, 30 * 1000L);

    }


}
