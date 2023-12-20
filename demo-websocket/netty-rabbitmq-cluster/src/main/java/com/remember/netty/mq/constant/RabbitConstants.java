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

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.system.SystemUtil;

import java.text.MessageFormat;

/**
 * @author wangjiahao
 * @date 2023/12/19 15:39
 */
public class RabbitConstants {

    /**
     * websocket 已登录的客户端(用户)
     */
    public static final String WS_CLIENT = "WS:CLIENT:ONLINE:";

    /**
     * 本级address ip
     */
    public static final String ADDRESS = SystemUtil.getHostInfo().getAddress();
    /**
     * 本级address ip md5
     */
    public static final String ADDRESS_MD5 = new Digester(DigestAlgorithm.MD5).digestHex(ADDRESS);

    private static final String PREFIX_INSTANCE = "instance_{0}";
    private static final String PREFIX_REPLY = "reply_{0}";

    /**
     * 获取实例的queue name
     * @param instanceId 实例id
     * @return queue name
     */
    public static String getInstanceQueueName(String instanceId) {
        return MessageFormat.format(PREFIX_INSTANCE, instanceId);
    }
    /**
     * 获取实例的routing key name
     * @param instanceId 实例id
     * @return routing key name
     */
    public static String getInstanceRoutingKeyName(String instanceId) {
        return MessageFormat.format(PREFIX_INSTANCE, instanceId);
    }

    /**
     * 获取一次性消息的reply queue name
     * @param messageId 消息id
     * @return queue name
     */
    public static String getReplyQueueName(String messageId) {
        return MessageFormat.format(PREFIX_REPLY, messageId);
    }
    /**
     * 获取一次性消息的routing key name
     * @param messageId 消息id
     * @return routing key name
     */
    public static String getReplyRoutingKeyName(String messageId) {
        return MessageFormat.format(PREFIX_REPLY, messageId);
    }

}
