---
title: "Java"
date: 2019-12-02
description: ""
tags: [Java]
categories: [笔记]
---

## 常用命令
- jar启动  
`java -Dfile.encoding=utf-8 -jar xxx.jar`

## 并发
>[EN](http://tutorials.jenkov.com/java-concurrency/index.html) [CN1](http://ifeve.com/java-concurrency-thread-directory/) [CN2](https://my.oschina.net/roccn?tab=newest&catalogId=5671817)
### 线程通信
- 信号丢失
- 假唤醒

## 坑
- 由于cglib是基于继承的方式实现类的动态代理，因此无法实现对final方法的代理。