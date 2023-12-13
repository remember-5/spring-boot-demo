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
package com.remember.netty.redis.properties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

/**
 * websocket 配置
 *
 * @author wangjiahao
 * @date 2023/12/12 11:02
 */
@Data
@Configuration
@ConfigurationProperties(prefix = WebSocketProperties.PREFIX)
public class WebSocketProperties {

    public static final String PREFIX = "web-socket";

    private Boolean enableCluster;

    @NestedConfigurationProperty
    private Netty netty;


    @Getter
    @Setter
    public static class Netty {

        private Integer port;

        private String path;

        private Integer backlog;

    }


}
