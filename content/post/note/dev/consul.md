---
title: "consul"
date: 2020-08-11T10:29:33+08:00
tags: [consul]
categories: [note]
---

## 单节点
```bash
mkdir -p /usr/local/consul-data/logs
nohup consul agent -server -data-dir=/usr/local/consul-data/ -node=agent-one -bind=0.0.0.0 -bootstrap-expect=1 -client=0.0.0.0 -ui > /usr/local/consul-data/logs/consul.log 2>&1 &
```


