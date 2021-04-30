package com.remember.rocketmq.demo.producer;

import com.remember.rocketmq.demo.message.Demo01Message;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

/**
 * 同步发送
 * 异步发送
 * @author wangjiahao
 * @date 2020/12/2
 */
@Component
@RequiredArgsConstructor
public class Demo01Producer {
    private final RocketMQTemplate rocketMQTemplate;

    public SendResult syncSend(Integer id) {
        // 创建Demo01Message
        Demo01Message message = new Demo01Message();
        message.setId(id);
        return rocketMQTemplate.syncSend(Demo01Message.TOPIC,message);
    }

    public void asyncSend(Integer id, SendCallback callback){
        // 创建 Demo01Message消息
        Demo01Message message = new Demo01Message();
        message.setId(id);
        rocketMQTemplate.asyncSend(Demo01Message.TOPIC,message,callback);
    }


    public void onewaySend(Integer id ){
        Demo01Message message = new Demo01Message();
        message.setId(id);
        rocketMQTemplate.sendOneWay(Demo01Message.TOPIC,message);
    }




}
