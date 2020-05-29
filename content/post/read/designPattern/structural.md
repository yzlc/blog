---
title: "设计模式 - 结构型模式"
date: 2019-07-15T10:32:28+08:00
tags: [设计模式]
categories: [阅读]
draft: false
hiddenFromHomePage: true
---

## 装饰模式（Decorator）【ConcreteComponent——▷Component（穿衣）◁——Decorator】
>动态地给一个对象添加一些额外的职责，就增加功能来说，装饰模式比生成子类更为灵活
![](/images/read/designPattern/structural/decorator.png)
### 优点
- 可以简化原有的类
- 可以去除相关类中重复的装饰逻辑

## 代理模式（Proxy）【RealSubject——▷Subject◁——Proxy】
>为其他对象提供一种代理以控制对这个对象的访问
![](/images/read/designPattern/structural/proxy.png)
### 应用
- 远程代理，也就是为一个对象在不同的地址空间提供局部代表。这样可以隐藏一个对象存在于不同地址空间的事实
- 虚拟代理，是根据需要创建开销很大的对象。通过它来存放实例化需要很长时间的真实对象
- 安全代理，用来控制真实对象访问时的权限
- 智能指引，是指当调用真实的对象时，代理处理另外一些事

## 外观模式（Facade）【SubSystemOne<——Facade（投资基金）——>SubSystemTwo】
>为子系统中的一组接口提供一个一致的界面，此模式定义了一个高层接口，这个接口使得这一子系统更加容易使用
![](/images/read/designPattern/structural/facade.png)
### 应用
- 设计初期阶段，层与层之间建立外观Facade
- 开发阶段，增加外观Facade可以提供一个简单的接口
- 维护遗留系统，提供清晰简单的接口

## 适配器模式（Adapter）【Target◁——Adapter（翻译）——>Adaptee】
>将一个类的接口转换成客户希望的另外一个接口。Adapter模式使得原本由于接口不兼容而不能一起工作的那些类可以一起工作
![](/images/read/designPattern/structural/adapter.png)

## 组合模式（Composite）【Leaf——▷Component（分公司-部门）◁——Composite】
>将对象组合成树形结构以表示‘部分-整体’的层次结构。组合模式使得用户对单个对象和组合对象的使用具有一致性
![](/images/read/designPattern/structural/composite.png)
### 应用
- 需求中是体现部分与整体层次的结构时，希望用户可以忽略组合对象与单个对象的不同，统一地使用组合结构中的所有对象
### 优点
- 定义了包含基本对象和层次结构。基本对象可以被组合成更复杂的组合对象，而这个组合对象又可以被组合，这样不断地递归下去，客户代码中，任何用到基本对象的地方都可以使用组合对象了
- 用户是不用关心到底是处理一个叶节点还是处理一个组合组件，也就用不着为定义组合而写一些选择判断语句了
- 组合模式让客户可以一致地使用组合结构和单个对象

## 桥接模式（Bridge）【Abstraction（手机品牌）◇——>Implementor（手机软件）】
>将抽象部分与它的实现部分分离，使它们都可以独立地变化
![](/images/read/designPattern/structural/bridge.png)

## 享元模式（Flyweight）【FlyweightFactory（网站共享代码）◇——>Flyweight】
>运用共享技术有效地支持大量细粒度的对象
![](/images/read/designPattern/structural/flyweight.png)
### 应用
- 如果一个应用程序使用了大量的对象，而大量的这些对象造成了很大的存储开销
- 对象的大多数状态可以外部状态，如果删除对象的外部状态，那么可以用相对较少的共享对象取代很多组对象