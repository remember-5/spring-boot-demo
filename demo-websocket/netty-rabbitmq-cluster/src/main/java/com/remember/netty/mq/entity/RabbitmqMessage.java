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
package com.remember.netty.mq.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author wangjiahao
 * @date 2023/12/20 21:33
 */
@Data
@Accessors(chain = true)
public class RabbitmqMessage {
    private String messageId;

    private String userId;

    private String message;
}
