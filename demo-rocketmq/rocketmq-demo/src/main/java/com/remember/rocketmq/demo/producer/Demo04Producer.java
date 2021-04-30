package com.remember.rocketmq.demo.producer;

import com.remember.rocketmq.demo.message.Demo04Message;
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
public class Demo04Producer {

    private final RocketMQTemplate rocketMQTemplate;

    public SendResult syncSend(Integer id ){
        Demo04Message message = new Demo04Message(id);
        return rocketMQTemplate.syncSend(Demo04Message.TOPIC,message);


    }



}
