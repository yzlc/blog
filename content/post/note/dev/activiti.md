---
title: "activiti"
date: 2020-07-29T16:07:33+08:00
tags: [activiti]
categories: [笔记]
---

>工作流开源框架
## 核心概念


## 快速入门
### BPMN
>业务流程建模与标注（Business Process Model and Notation，BPMN) ，描述流程的基本符号，包括这些图元如何组合成一个业务流程图

### 表
>框架自动创建
```
ACT_RE_*: repository。 流程定义和流程静态资源 （图片、规则等）
ACT_RU_*: runtime 运行时表（流程实例、任务、变量、异步任务等运行中的数据）。流程实例执行过程中保存， 流程结束时删除。 这样运行时表可以一直很小速度很快
ACT_ID_*: identity 身份信息（用户、组等）
ACT_HI_*: history 历史数据（历史流程实例、变量、任务等）
ACT_GE_*: general 通用数据， 用于不同场景下（资源文件）
```
### idea插件


## API