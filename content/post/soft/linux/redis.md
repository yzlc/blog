---
title: "redis"
date: 2019-09-01T10:32:33+08:00
tags: [redis]
categories: [笔记]
---

## redis
1. 安装
```bash
wget http://download.redis.io/releases/redis-5.0.3.tar.gz
tar xzf redis-5.0.3.tar.gz
cd redis-5.0.3
make
```

2. 配置
```bash
#修改配置文件
vi ./redis.conf

#daemonize设置为yes
daemonize yes
```
3. 启动
`src/redis-server ./redis.conf`