/**
 * Copyright [2022] [remember5]
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.remember5.mqtt.config;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Nullable;

/**
 * @author wangjiahao
 * @date 2024/3/15 17:50
 */
@Slf4j
@Configuration
public class MqttCallBack implements MqttCallback {

    private final MqttClient mqttClient;
    @Autowired
    public MqttCallBack(@Nullable MqttClient mqttClient) {
        this.mqttClient = mqttClient;
    }

    @Override
    public void connectionLost(Throwable throwable) {
        if (mqttClient == null || !mqttClient.isConnected()) {
            log.info("连接断开，正在重连....");
            try {
                mqttClient.connect();
                if(mqttClient.isConnected()){
                    log.info("mqtt重连完成.");
                }
            } catch (MqttException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        log.info("接收消息主题 : " + topic);
        log.info("接收消息内容 : " + new String(message.getPayload()));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        log.info("deliveryComplete---------" + token.isComplete());

    }
}
