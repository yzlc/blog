---
title: "nio"
date: 2021-04-28T14:55:33+08:00
tags: [nio,java]
categories: [note]
---

>Non-blocking IO。IO面向字节流和字符流（文件）；NIO面向通道和缓冲区（网络）
## Channel
>通道

- 类型：FileChannel、DatagramChannel（UDP）、SocketChannel（TCP）、ServerSocketChannel（TCP监听）
- 方法：read、register、configureBlocking
## Buffer
>缓冲区

- 属性：capacity容量、position位置、limit上限、mark标记position通过reset恢复
- 类型：ByteBuffer、MappedByteBuffer、CharBuffer。。。
- 方法：allocate、flip（读模式）、put、get
## Selector
>选择器

- 方法：open、selectedKeys、select、
- SelectionKey监听状态：OP_CONNECT、OP_ACCEPT、OP_READ、OP_WRITE