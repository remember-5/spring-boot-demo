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

import com.remember5.mqtt.properties.MqttProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author wangjiahao
 * @date 2024/3/15 17:49
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MqttConfig {

    private final MqttProperties mqttProperties;


    @Bean
    public MqttConnectOptions mqttConnectOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(mqttProperties.getUsername());
        options.setPassword(mqttProperties.getPassword().toCharArray());
        options.setCleanSession(true);
        // 设置超时时间，单位为秒
        options.setConnectionTimeout(30);
        //设置心跳时间 单位为秒，表示服务器每隔1.5*20秒的时间向客户端发送心跳判断客户端是否在线
        options.setKeepAliveInterval(20);
        return options;
    }

    @Bean
    @ConditionalOnBean(MqttConnectOptions.class)
    public MqttClient mqttClient(MqttConnectOptions mqttConnectOptions) {
        try {
            //创建MQTT客户端对象
            MqttClient client = new MqttClient(mqttProperties.getUrl(), mqttProperties.getClientId());

            client.setCallback(new MqttCallBack(client));
            IMqttToken iMqttToken = client.connectWithResult(mqttConnectOptions);
            boolean complete = iMqttToken.isComplete();
            log.info("mqtt建立连接：{}", complete);
            return client;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("mqtt 连接异常");
        }
    }

}
