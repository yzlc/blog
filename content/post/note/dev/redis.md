---
title: "redis"
date: 2021-04-23T15:08:33+08:00
tags: [redis,缓存,中间件]
categories: [note]
---

>内存数据库。丰富的数据类型、灾难恢复、集群、过期策略

## 数据结构
| 类型       | 命令                                                  | 场景                                                               |
| ---------- | ----------------------------------------------------- | ------------------------------------------------------------------ |
| string     | set,get,strlen,exists,decr,incr,setex                 | 计数                                                               |
| list       | rpush,lpop,lpush,rpop,lrange、llen                    | 发布订阅、消息队列、慢查询                                         |
| hash       | hset,hmset,hexists,hget,hgetall,hkeys,hvals           | 系统中对象数据的存储                                               |
| set        | sadd,spop,smembers,sismember,scard,sinterstore,sunion | 不重复以及获取交集和并集                                           |
| sorted set | zadd,zcard,zscore,zrange,zrevrange,zrem               | 根据某个权重排序。比如直播排行、用户列表、礼物排行、弹幕           |
| bitmap     | setbit 、getbit 、bitcount、bitop                     | 状态信息（是否签到）并进一步分析。比如用户行为（是否点赞某个视频） |