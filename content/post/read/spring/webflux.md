---
title: "springWebflux"
date: 2020-12-12T11:38:33+08:00
tags: [spring]
categories: [read]
---

异步：调用者发送请求，不等待回应就做事情
非阻塞：被调用者收到请求，先反馈再做事情

## 响应式编程
>观察者模式，jdk8：Observale，jdk9：Flow
### Reactor
>Reactive规范

核心类：发布者Mono（返回0或1个元素）、Flux（返回N个元素）实现Publisher接口。发出三种数据信号：元素值，终止信号（错误、完成）
