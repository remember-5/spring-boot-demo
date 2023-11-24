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
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.impl.DefaultProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author wangjiahao
 * @date 2023/11/23 15:45
 */

@RestController
@RequestMapping("/flowable")
@RequiredArgsConstructor
public class FlowableController {

    private final RuntimeService runtimeService;
    private final TaskService taskService;
    private final RepositoryService repositoryService;
    private final ProcessEngineConfiguration processEngineConfiguration;


    @GetMapping("/allDeploy")
    public List allDeploy() {
        // 获取流程定义信息
        final List<Deployment> list = repositoryService.createDeploymentQuery().list();
        for (Deployment deployment : list) {
            System.err.println("部署id: " + deployment.getId());
            System.err.println("部署名称: " + deployment.getName());
            System.err.println("部署时间: " + deployment.getDeploymentTime());
            System.err.println("#############################################");
        }
        return list;
    }

    @GetMapping("/deployById")
    public String getDeployById(String id) {
        // 获取流程定义信息
        final Deployment deployment = repositoryService.createDeploymentQuery()
                .deploymentId(id)
                .singleResult();
        System.err.println("部署id: " + deployment.getId());
        System.err.println("部署名称: " + deployment.getName());
        System.err.println("部署时间: " + deployment.getDeploymentTime());
        System.err.println("#############################################");
        return "success";
    }

    /**
     * 设置流程的环境变量
     *
     * @param processDefinitionId 流程定义id
     * @param variableName        变量名
     * @param variableValue       变量值
     * @return success
     */
    @PostMapping("/setVariable")
    public String setVariable(String processDefinitionId, String variableName, String variableValue) {
        runtimeService.setVariable(processDefinitionId, variableName, variableValue);
        return "success";
    }


    /**
     * 获取待办的流程id和任务id
     *
     * @param approverId 审批人id
     * @return 流程id和任务id
     */
    @GetMapping("/todo")
    public Map<String, List<Task>> getTodoTask(String approverId) {
        // 获取待办任务
        final List<Task> list = taskService.createTaskQuery()
                .taskAssignee(approverId)
                .list();
        HashMap<String, List<Task>> map = new HashMap<>();
        for (Task task : list) {
            System.err.println("任务id: " + task.getId());
            System.err.println("任务名称: " + task.getName());
            System.err.println("任务创建时间: " + task.getCreateTime());
            System.err.println("任务办理人: " + task.getAssignee());
            System.err.println("流程实例id: " + task.getProcessInstanceId());
            System.err.println("执行对象id: " + task.getExecutionId());
            System.err.println("流程定义id: " + task.getProcessDefinitionId());
            System.err.println("#############################################");
            if (map.containsKey(task.getProcessInstanceId())) {
                final List<Task> tasks = map.get(task.getProcessInstanceId());
                tasks.add(task);
            } else {
                map.put(task.getProcessInstanceId(), new ArrayList<>(Arrays.asList(task)));
            }


        }
        System.err.println(map);
        return map;
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
