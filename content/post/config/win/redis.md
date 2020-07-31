---
title: "redis"
date: 2020-07-31T13:50:33+08:00
tags: [redis]
categories: [配置]
---

## [集群脚本](/files/read/config/win/redis.zip)
1. redis根目录新建cluster文件夹
2. 拷贝3节点配置文件到cluster文件夹
   - redis.7000.conf
   - redis.7001.conf
   - redis.7002.conf
3. 修改对应配置项
    ```properties
    port 7000
    cluster-enabled yes
    cluster-config-file nodes.7000.conf
    cluster-node-timeout 5000
    appendonly yes
    ```
4. redis根目录，编写脚本cluster.bat
    ```bat
    start redis-server cluster/redis.7000.conf
    start redis-server cluster/redis.7001.conf
    start redis-server cluster/redis.7002.conf

    redis-cli --cluster create 127.0.0.1:7000 127.0.0.1:7001 127.0.0.1:7002 --cluster-yes
    ```