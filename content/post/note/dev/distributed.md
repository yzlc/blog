---
title: "分布式"
date: 2021-05-12T09:52:33+08:00
tags: [分布式]
categories: [note]
---

>拆分成多个子服务,将这些子服务都部署在不同的服务器上

## [CAP](https://zh.wikipedia.org/wiki/CAP%E5%AE%9A%E7%90%86)
>因为P的存在，要求我们在A和C中做出权衡

- 一致性（Consistency）：所有节点访问同一份最新数据
- 可用性（Availability）：每次请求都能获取到非错的响应
- 分区容错性（Partition tolerance）：对通信的时限要求

## Paxos算法
>消息传递且具有高度容错特性的一致性算法，其解决的问题就是在分布式系统中如何就某个值（决议）达成一致。Proposer提案者、Acceptor表决者、Learner学习者。两个阶段，分别为 Prepare和accept阶段

### prepare阶段
- Proposer提案者：负责提出 proposal，每个提案者在提出提案时都会首先获取到一个 具有全局唯一性的、递增的提案编号N，即在整个集群中是唯一的编号 N，然后将该编号赋予其要提出的提案，在第一阶段是只将提案编号发送给所有的表决者
- Acceptor表决者：每个表决者在 accept 某提案后，会将该提案编号N记录在本地，这样每个表决者中保存的已经被 accept 的提案中会存在一个编号最大的提案，其编号假设为maxN。每个表决者仅会 accept 编号大于自己本地 maxN 的提案，在批准提案时表决者会将以前接受过的最大编号的提案作为响应反馈给Proposer

### accept阶段
当一个提案被 Proposer 提出后，如果 Proposer 收到了超过半数的 Acceptor 的批准（Proposer 本身同意），那么此时 Proposer 会给所有的 Acceptor 发送真正的提案（你可以理解为第一阶段为试探），这个时候 Proposer 就会发送提案的内容和提案编号。

表决者收到提案请求后会再次比较本身已经批准过的最大提案编号和该提案编号，如果该提案编号 大于等于 已经批准过的最大提案编号，那么就 accept 该提案（此时执行提案内容但不提交），随后将情况返回给 Proposer 。如果不满足则不回应或者返回 NO 。

paxos第二阶段1

当 Proposer 收到超过半数的 accept ，那么它这个时候会向所有的 acceptor 发送提案的提交请求。需要注意的是，因为上述仅仅是超过半数的 acceptor 批准执行了该提案内容，其他没有批准的并没有执行该提案内容，所以这个时候需要向未批准的 acceptor 发送提案内容和提案编号并让它无条件执行和提交，而对于前面已经批准过该提案的 acceptor 来说 仅仅需要发送该提案的编号 ，让 acceptor 执行提交就行了。

paxos第二阶段2

而如果 Proposer 如果没有收到超过半数的 accept 那么它将会将 递增 该 Proposal 的编号，然后 重新进入 Prepare 阶段 