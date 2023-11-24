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
package com.remember5.flowable.controller;

import lombok.RequiredArgsConstructor;
import org.flowable.engine.*;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangjiahao
 * @date 2023/11/23 15:45
 */

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final RuntimeService runtimeService;
    private final TaskService taskService;

    /**
     * 启动流程实例
     *
     * @param approverId 审批领导id
     * @return 流程实例id
     */
    @GetMapping(value = "/start")
    public String startProcess(String approverId) {
        // 启动流程实例
        final HashMap<String, Object> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("level_1_user", approverId);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("LeaveApplication", stringStringHashMap);
        System.out.println("流程实例ID: " + processInstance.getId());
        System.out.println("流程定义ID: " + processInstance.getProcessDefinitionId());

        // 获取流程实例的环境变量
        final String id = processInstance.getId();
        runtimeService.setVariable(id, "level_1_user", approverId);

        return id;
    }


    /**
     * @param taskId 任务id
     * @param status 审批状态
     * @return
     */
    @GetMapping(value = "/approver")
    public String startLeaveProcess(String taskId, String status) {
        // 判断审批人是否匹配,在jwt中获取user

        // 获取当前任务的环境变量
        final Map<String, Object> variables = runtimeService.getVariables(taskId);
        System.err.println(variables);
        String approverId = (String) variables.get("level_1_user");
        if (!approverId.equals("1")) {
            throw new RuntimeException("审批人不匹配");
        }
        // 获取下一步任务 这个暂时用不到
//        final Task task = taskService.createTaskQuery()
//                .processInstanceId(taskId)
//                .singleResult();

        // 设置当前任务的环境变量
        final HashMap<String, Object> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("status", status);
        taskService.complete(taskId, stringStringHashMap);
        return "审批完成~";

    }


}
