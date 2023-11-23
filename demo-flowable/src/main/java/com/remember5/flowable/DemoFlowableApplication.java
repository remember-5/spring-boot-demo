package com.remember5.flowable;

import lombok.RequiredArgsConstructor;
import org.flowable.engine.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class DemoFlowableApplication implements CommandLineRunner {
    private final RuntimeService runtimeService;
    private final TaskService taskService;
    private final RepositoryService repositoryService;
    private final ProcessEngine processEngine;
    private final ProcessEngineConfiguration processEngineConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(DemoFlowableApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        String leve1User = "1";
//
//        // 启动流程实例
//        final HashMap<String, Object> stringStringHashMap = new HashMap<>();
//        stringStringHashMap.put("level_1_user", leve1User);
//        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("LeaveApplication", stringStringHashMap);
//        System.out.println("流程实例ID: " + processInstance.getId());
//        System.out.println("流程定义ID: " + processInstance.getProcessDefinitionId());
//
//        // 获取流程实例的环境变量
//        final String id = processInstance.getId();
//        runtimeService.setVariable(id, "level_1_user", leve1User);
//
//        // 获取变量
//        System.err.println("level_1_user : " + runtimeService.getVariable(id, "level_1_user"));
//
//        // 获取1级审批
//        final Task task1 = taskService.createTaskQuery()
//                .processInstanceId(id)
//                .singleResult();
//
//        HashMap<String, Object> hashMap = new HashMap<>();
//        hashMap.put("status", "approve");
//        taskService.complete(task1.getId(), hashMap);
//
//        final List<Task> list1 = taskService.createTaskQuery().taskAssignee("zhangsan").list();
//        System.err.println("zhangsan: " + list1);
//
//
//        // 获取2级审批
//        final Task task2 = taskService.createTaskQuery()
//                .processInstanceId(id)
//                .singleResult();
//
//        // 查询任务
//        final List<Task> list = taskService.createTaskQuery().taskAssignee(leve1User).list();
//        System.err.println(list);

    }

}
