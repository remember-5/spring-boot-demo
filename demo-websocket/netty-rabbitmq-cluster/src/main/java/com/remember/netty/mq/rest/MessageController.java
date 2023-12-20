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
package com.remember.netty.mq.rest;

import com.remember.netty.mq.entity.RabbitmqMessage;
import com.remember.netty.mq.manager.NettyChannelManager;
import com.remember.netty.mq.service.MessageService;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @author wangjiahao
 * @date 2023/12/20 20:50
 */
@Slf4j
@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/sendLocalMessage")
    public Mono<String> sendLocalMessage(@RequestBody RabbitmqMessage rabbitmqMessage) {
        return messageService.sendLocalMessage(rabbitmqMessage);
    }

    @PostMapping("/sendMessage")
    public Mono<String> sendMessage(@RequestBody RabbitmqMessage rabbitmqMessage) {
        return messageService.sendMessage(rabbitmqMessage);
    }

    @GetMapping
    public void test() {
        NettyChannelManager.getChannelGroup().writeAndFlush(new TextWebSocketFrame("全体消息!"));
    }

}
