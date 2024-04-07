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
package com.remember5.mqtt.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author wangjiahao
 * @date 2024/3/15 18:29
 */
@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class TestController {

    private final MqttClient mqttClient;

    @GetMapping
    public String test(String msg) {
        try {
            // topic规则:
            MqttMessage mqttMessage = new MqttMessage();
            mqttMessage.setQos(0);
            mqttMessage.setRetained(true);
            mqttMessage.setPayload(msg.getBytes());
            mqttClient.publish("/01/01/openTable", mqttMessage);

            CompletableFuture<String> future = new CompletableFuture<>();
            mqttClient.subscribe("/01/01/openTableCallback", (topic, message) -> {
                log.info("接收消息主题 : " + topic);
                log.info("接收消息内容 : " + new String(message.getPayload()));
                String payload = new String(message.getPayload());
                // 取消订阅
                mqttClient.unsubscribe("/01/01/openTableCallback");
                future.complete(payload);
            });
            String receivedMessage = future.get(20, TimeUnit.SECONDS);
            // 在这里对接收到的消息进行解析
            log.info("接收到的消息：" + receivedMessage);
            if ("开台成功".equals(receivedMessage)) {
                return "开台成功";
            } else {
                return "开台状态异常";
            }

        } catch (InterruptedException e) {
            return "error";
        } catch (MqttPersistenceException e) {
            return "error";
        } catch (MqttException e) {
            return "error";
        } catch (ExecutionException e) {
            return "error";
        } catch (TimeoutException e) {
            return "error";
        }
    }

    public static String test2() {
        CompletableFuture future = CompletableFuture.supplyAsync(() -> {
            System.out.println("投放和清洗制作米饭的材料");
            return "干净的没有新冠病毒的大米";
        }).thenAcceptAsync(result -> {
            System.out.println("通电，设定模式，开始煮米饭");
        }).thenRunAsync(() -> {
            System.out.println("米饭做好了，可以吃了");
        });
        return "fail";
    }

    public static void main(String[] args) {
        test2();
    }

}
