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
package com.remember5.mongodb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * @author wangjiahao
 * @date 2024/3/31 20:39
 */
@Data
@Accessors(chain = true)
@Document(collection = "java_gpt_chat")
public class GptChat {

    @Id
    private String id;
    /**
     * 渠道id
     */
    private String channelId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户类型(1代表unionid、2代表externalUserId)
     */
    private int userType = 3;
    /**
     * 模型
     */
    private String modelType;
    /**
     * 投票(0 点踩1 点赞)
     */
    private int vote;
    /**
     * 点踩反馈
     */
    private String voteDesc;

    /**
     * 创建时间
     */
    @JsonFormat( pattern ="yyyy-MM-dd")
    private Date createTime;

    /**
     * 对话内容
     */
    private List<ChatContent> content;
}
