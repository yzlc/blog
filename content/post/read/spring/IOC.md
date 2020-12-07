---
title: "springIOC"
date: 2020-12-07T22:08:33+08:00
tags: [spring]
categories: [read]
---

>把对象创建和调用交给spring管理，降低耦合。实现：配置解析->工厂->创建对象(反射)->DI(注入属性)

接口
- BeanFactory：IOC容器基本实现，懒加载
- ApplicationContext：加载配置时创建对象