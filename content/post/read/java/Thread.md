---
title: "线程"
date: 2020-12-13T15:19:33+08:00
tags: [Java]
categories: [read]
---

继承Thread implements Runnable
- Thread，static类、方法实现数据共享
- Runnable，数据共享
- Collable，返回值、抛异、泛型

sleep、wait
- Thread类sleep, Object类wait
- wait必须在同步代码块或同步方法中，调用者持有同步代码块或同步方法锁
- wait会释放锁

## 线程池
ExecutorService接口，常用子类ThreadPoolexecutor，工具类Executors
- Executors.newCachedThreadPool()：可根据需要创建新线程
- Executors.newFixedthreadPool(n)：可重用固定线程
- EXecutors.newSingleThreadEXecutor()：一个线程
- Executors.newThreadPoo(n)：延迟或定期