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
package com.remember.netty.constant;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.system.SystemUtil;

/**
 * netty redis 中需要用的key
 *
 * @author wangjiahao
 * @date 2023/12/12
 */
public interface NettyRedisConstants {

    /**
     * 本级address ip
     */
    String ADDRESS = SystemUtil.getHostInfo().getAddress();
    /**
     * 本级address ip md5
     */
    String ADDRESS_MD5 = new Digester(DigestAlgorithm.MD5).digestHex(ADDRESS);
    /**
     * redis发布订阅topic：发送给指定用户
     */
    String PUSH_MESSAGE_TO_ONE = "PushMessageToOne";

    /**
     * redis发布订阅topic：发送给所有用户
     */
    String PUSH_MESSAGE_TO_ALL = "PushMessageToAll";


    /**
     * websocket 已登录的客户端(用户)
     */
    String WS_CLIENT = "WS:CLIENT:";

    String REDIS_WEB_SOCKET_USER_SET = "WS:USER";


}
