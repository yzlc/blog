---
title: "array"
date: 2021-02-20T19:15:33+08:00
tags: [algorithms]
categories: [note]
---

## 稀疏数组
>压缩棋盘。记录数组一共有几行几列，有多少个不同的值

|     |     |     |     |     |     |     |
| --- | --- | --- | --- | --- | --- | --- |
| 11  | 22  | 0   | 0   | 0   | 0   | 0   |
| 0   | 33  | 44  | 0   | 0   | 0   | 0   |
| 0   | 0   | 55  | 66  | 77  | 0   | 0   |
| 0   | 0   | 0   | 0   | 0   | 88  | 0   |
| 0   | 0   | 0   | 0   | 0   | 0   | 99  |


|     | 行  | 列  | 值  |
| --- | --- | --- | --- |
| [0] | 5   | 7   | 9   |
| [1] | 0   | 0   | 11  |
| [2] | 0   | 1   | 22  |
| [3] | 1   | 1   | 33  |
| [4] | 1   | 2   | 44  |
| [5] | 2   | 2   | 55  |
| [6] | 2   | 3   | 66  |
| [7] | 2   | 4   | 77  |
| [8] | 3   | 5   | 88  |
| [9] | 4   | 6   | 99  |

## 环形队列
### 限制rear赶上front，即队尾结点与队首结点之间至少留有一个元素的空间
>考虑rear<front的情况

![](/images/note/dev/algorithms/circularDeque.png "环形队列")

- 队列空：front==rear
- 队列满：(rear+1)%size==front
- 队列长度：(rear-front+size)%size