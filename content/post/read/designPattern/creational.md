---
title: "创建型模式"
date: 2019-07-15T11:32:28+08:00
tags: [设计模式]
categories: [read]
hiddenFromHomePage: true
---

## 简单工厂模式
![](/images/read/designPattern/creational/simpleFactory.png)
### 优点
- 工厂类中包含了必要的逻辑判断，根据客户端的选择条件动态实例化相关的类，对于客户端来说，去除了与具体产品的依赖
### 应用
- 用反射去除switch或if，解除分支判断带来的耦合

## 工厂方法模式（Factory Method）
>定义一个用于创建对象的接口，让子类决定实例化哪一个类。工厂方法使一个类的实例化延迟到其子类

![](/images/read/designPattern/creational/factoryMethod.png)
### vs 简单工厂
- 把简单工厂的内部逻辑判断移到了客户端代码来进行

## 原型模式（Prototype）
>用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象

![](/images/read/designPattern/creational/prototype.png)
### 优点
- 一般在初始化的信息不发生变化的情况下，克隆是最好的办法。既隐藏了对象创建的细节，又对性能是大大的提高
- 不用重新初始化对象，而是动态地获得对象运行时的状态

## 建造者模式（Builder）
>将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示

![](/images/read/designPattern/creational/builder.png)
### 应用
- 创建复杂的对象，这些对象内部构建间的建造顺序通常是稳定的，但对象内部的构建通常面临着复杂的变化
- 在当创建复杂对象的算法应该独立于该对象的组成部分以及它们的装配方式时适用
### 优点
- 使得建造代码与表示代码分离，由于建造者隐藏了该产品是如何组装的，所以若需要改变一个产品的内部表示，只需要再定义一个具体的建造者就可以了

## 抽象工厂模式（AbstractFactory）
>提供一个创建一系列相关或相互依赖对象的接口，而无需指定它们具体的类

![](/images/read/designPattern/creational/abstractFactory.png)
### 优点
- 易于交换产品系列，由于具体工厂类，在一个应用中只需要在初始化的时候出现一次，这就使得改变一个应用的具体工厂变得非常容易，它只需要改变具体工厂即可使用不同的产品配置
- 让具体的创建实例过程与客户端分离，客户端是通过它们的抽象接口操纵实例，产品的具体类名也被具体工厂的实现分离，不会出现在客户代码中
### 缺点
- 增加产品改动太大
### 改进
- 简单工厂
- 反射
- 配置文件

## 单例模式（Singleton）
>保证一个类仅有一个实例，并提供一个访问它的全局访问点

![](/images/read/designPattern/creational/singleton.png)