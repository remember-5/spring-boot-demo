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
import org.apache.commons.io.IOUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.*;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.impl.DefaultProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
    private final RepositoryService repositoryService;
    private final ProcessEngine processEngine;
    private final ProcessEngineConfiguration processEngineConfiguration;

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
        // 获取下一步任务
        final Task task = taskService.createTaskQuery()
                .processInstanceId(taskId)
                .singleResult();

        // 设置当前任务的环境变量
        final HashMap<String, Object> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("status", status);
        taskService.complete(task.getId(), stringStringHashMap);
        return "审批完成~";

    }


    /**
     * 获取流程图
     *
     * @param processInstanceId 流程实例id
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/image/{processInstanceId}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getTaskImage(@PathVariable String processInstanceId) throws IOException {
        // 获取流程定义 ID
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();

        String processDefinitionId = processInstance.getProcessDefinitionId();

        // 获取流程模型
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);

        // 获取活动节点
        List<String> activeActivityIds = runtimeService.getActiveActivityIds(processInstanceId);

        // 生成流程图
        InputStream imageStream = new DefaultProcessDiagramGenerator().generateDiagram(
                bpmnModel, "png", activeActivityIds,
                Collections.emptyList(), processEngineConfiguration.getActivityFontName(),
                processEngineConfiguration.getLabelFontName(),
                processEngineConfiguration.getAnnotationFontName(),
                processEngineConfiguration.getClassLoader(), 1.0, true);
        // 将InputStream转换为byte数组
        byte[] imageBytes = IOUtils.toByteArray(imageStream);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageBytes);
    }

}
