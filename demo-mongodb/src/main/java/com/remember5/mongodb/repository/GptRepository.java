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
package com.remember5.mongodb.repository;

import com.remember5.mongodb.entity.GptChat;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author wangjiahao
 * @date 2024/3/31 20:40
 */
public interface GptRepository extends MongoRepository<GptChat, String> {

}
