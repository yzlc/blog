---
title: "ZooKeeper"
date: 2021-05-12T09:23:33+08:00
tags: [ZooKeeper,分布式,中间件]
categories: [note]
---

>分布式协调服务（动物园管理员）。提供了高可用、高性能、稳定的分布式数据一致性解决方案，通常被用于实现诸如数据发布/订阅、负载均衡、命名服务、分布式协调/通知、集群管理、Master 选举、分布式锁和分布式队列等功能

## Data model（数据模型）
>多叉树形结构，根节点以“/”来代表。数据节点被称为znode（"/"路径表示）

### znode类型
>znode由2部分组成：stat（状态信息）、data（数据）

- 持久（PERSISTENT）节点：一旦创建就一直存在即使ZooKeeper集群宕机，直到将其删除。
- 临时（EPHEMERAL）节点：临时节点的生命周期是与 客户端会话（session） 绑定的，会话消失则节点消失 。并且，临时节点只能做叶子节点 ，不能创建子节点。
- 持久顺序（PERSISTENT_SEQUENTIAL）节点：除了具有持久（PERSISTENT）节点的特性之外， 子节点的名称还具有顺序性。比如 /node1/app0000000001 、/node1/app0000000002 。
- 临时顺序（EPHEMERAL_SEQUENTIAL）节点：除了具备临时（EPHEMERAL）节点的特性之外，子节点的名称还具有顺序性。

## ACL（权限控制）
### znode
- CREATE : 能创建子节点
- READ ：能获取节点数据和列出其子节点
- WRITE : 能设置/更新节点数据
- DELETE : 能删除子节点
- ADMIN : 能设置节点 ACL 的权限

### 身份认证
- world ： 默认方式，所有用户都可无条件访问。
- auth :不使用任何 id，代表任何已认证的用户。
- digest :用户名:密码认证方式： username:password 。
- ip : 对指定 ip 进行限制。

## Watcher（事件监听器）
>允许用户在指定节点上注册一些 Watcher，并且在一些特定事件触发的时候，ZooKeeper 服务端会将事件通知到感兴趣的客户端上去，该机制是 ZooKeeper 实现分布式协调服务的重要特性
