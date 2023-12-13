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
package com.remember.netty.controller;

import com.remember.netty.entity.NettyPushMessageBody;
import com.remember.netty.properties.WebSocketProperties;
import com.remember.netty.service.PushService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author sixiaojie
 * @date 2020-03-30-20:08
 */
@Slf4j
@RestController
@RequestMapping("/push")
@RequiredArgsConstructor
public class PushController {

    private final PushService pushService;
    private final WebSocketProperties webSocketProperties;

    @PostMapping("/localPush2User")
    public void localPush2User(@RequestBody NettyPushMessageBody nettyPushMessageBody) {
        pushService.localPush2User(nettyPushMessageBody);
    }

    @PostMapping("/localPushAllUser")
    public void localPushAllUser(@RequestBody NettyPushMessageBody nettyPushMessageBody) {
        pushService.localPushAllUser(nettyPushMessageBody);
    }

    @PostMapping("/pushMsg2User")
    public void pushMsg2User(@RequestBody NettyPushMessageBody nettyPushMessageBody) {
        if (Boolean.TRUE.equals(webSocketProperties.getEnableCluster())) {
            pushService.pushMsg2User(nettyPushMessageBody);
        }
    }

    @PostMapping("/pushMsg2AllUser")
    public void pushMsg2AllUser(@RequestBody NettyPushMessageBody nettyPushMessageBody) {
        if (Boolean.TRUE.equals(webSocketProperties.getEnableCluster())) {
            pushService.pushMsg2AllUser(nettyPushMessageBody);
        }
    }

}
