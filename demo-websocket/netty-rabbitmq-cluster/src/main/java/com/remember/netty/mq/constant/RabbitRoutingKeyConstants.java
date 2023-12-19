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
package com.remember.netty.mq.constant;

import java.text.MessageFormat;

/**
 * @author wangjiahao
 * @date 2023/12/19 15:39
 */
public class RabbitRoutingKeyConstants {


    /**
     * websocket 已登录的客户端(用户)
     */
    public static final String WS_CLIENT = "WS:CLIENT:ONLINE";

    private static final String PREFIX_CONSUMER = "INSTANCE.{0}";

    public static String getPrefixConsumer(String instanceId) {
        return MessageFormat.format(PREFIX_CONSUMER, instanceId);
    }


}
