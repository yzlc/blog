---
title: "activiti"
date: 2020-07-29T16:07:33+08:00
tags: [activiti]
categories: [note]
---

>工作流开源框架
## 快速入门
### BPMN
>业务流程建模与标注（Business Process Model and Notation，BPMN) ，描述流程的基本符号，包括这些图元如何组合成一个业务流程图

### 表
>自动创建
```
ACT_RE_*: repository。 流程定义和流程静态资源 （图片、规则等）
ACT_RU_*: runtime 运行时表（流程实例、任务、变量、异步任务等运行中的数据）。流程实例执行过程中保存， 流程结束时删除。 这样运行时表可以一直很小速度很快
ACT_ID_*: identity 身份信息（用户、组等）
ACT_HI_*: history 历史数据（历史流程实例、变量、任务等）
ACT_GE_*: general 通用数据， 用于不同场景下（资源文件）
```
### [idea插件](https://plugins.jetbrains.com/plugin/7429-actibpm)
>放在`resources/processes`目录

![](/images/note/activiti/流程.png "流程")

![](/images/note/activiti/任务.png "任务")

![](/images/note/activiti/连线.png "连线")

### maven
```xml
<dependency>
    <groupId>org.activiti</groupId>
    <artifactId>activiti-spring-boot-starter-basic</artifactId>
    <version>5.22.0</version>
</dependency>
```

### properties
```properties
# 自动部署，默认true
spring.activiti.check-process-definitions=false
#JobExecutor是管理一系列线程的组件，可以触发定时器，关闭可以避免与job发生冲突，JobExecutor对多线程处理比较缓慢
spring.activiti.job-executor-activate=false
```

## API
```java
@Autowired
private RepositoryService repositoryService;
@Autowired
private RuntimeService runtimeService;
@Autowired
private TaskService taskService;
@Autowired
private HistoryService historyService;
```

### 部署
```java
public void deploy(String resource) {
    DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
    deploymentBuilder.addClasspathResource(resource);
    deploymentBuilder.deploy();
}
```
### 执行
```java
public ProcessInstance start(String processDefinitionKey, Map<String, Object> variables) {
    ProcessInstance processInstance = Objects.isNull(variables) ?
            runtimeService.startProcessInstanceByKey(processInstanceByKey) :
            runtimeService.startProcessInstanceByKey(processInstanceByKey, variables);
    
    System.out.println("流程实例id:" + processInstance.getId());
    System.out.println("流程定义id:" + processInstance.getProcessDefinitionId());
    return processInstance;
}
```
### 查询任务
```java
public List<Task> query(String assignee) {
    return taskService.createTaskQuery().taskAssignee(assignee).list();
}
```
### 处理任务
```java
public void complete(String taskId) {
    taskService.complete(taskId);
}
```