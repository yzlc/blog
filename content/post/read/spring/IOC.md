---
title: "springIOC"
date: 2020-12-07T22:08:33+08:00
tags: [spring]
categories: [read]
---

思想：把对象创建和调用交给spring管理，降低耦合

实现：配置解析->工厂->创建对象(反射)->DI(注入属性)

## beans
创建

![](/images/read/spring/DefaultListableBeanFactory.jpg)

- BeanFactory：IOC容器基本实现，懒加载
- ListableBeanFactory：Bean是可列表的
- HierarchicalBeanFactory：Bean有继承关系
- AutowireCapableBeanFactory：Bean的自动装配规则
- DefaultListableBeanFactory：默认实现

定义

![](/images/read/spring/RootBeanDefinition.jpg)

解析

![](/images/read/spring/XmlBeanDefinitionReader.jpg)

## context
>给Spring提供运行时的环境（Ioc容器），用以保存各个对象的状态。整合了大部分功能

![](/images/read/spring/ClassPathXmlApplicationContext.jpg)

- ResourceLoader：访问外部资源
- ConfigurableApplicationContext：可动态添加或修改已有的配置信息
    - AbstractRefreshableApplicationContext（常用）

作用
- 标识应用环境
- 创建Bean对象
- 保存对象关系表
- 捕获各种事件

## core：
>定义了资源的访问方式
