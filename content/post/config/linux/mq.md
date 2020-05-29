---
title: "rabbitmq"
date: 2019-09-01T10:32:33+08:00
tags: [rabbitmq]
categories: [配置]
---

## 下载脚本
```bash
curl -s https://packagecloud.io/install/repositories/rabbitmq/rabbitmq-server/script.rpm.sh | sudo bash
curl -s https://packagecloud.io/install/repositories/rabbitmq/erlang/script.rpm.sh | sudo bash
```
## 安装
```bash
yum  -y install erlang
yum -y install rabbitmq-server
```
## 命令
```bash
#启动服务
systemctl start rabbitmq-server

#查看状态
systemctl status rabbitmq-server

#设置为开机启动
systemctl enable rabbitmq-server
```
## 添加用户并授权
```bash
#添加用户
rabbitmqctl add_user admin admin

#设置用户角色
rabbitmqctl set_user_tags admin administrator

#tag（administrator，monitoring，policymaker，management）

#设置用户权限(接受来自所有Host的所有操作)
rabbitmqctl  set_permissions -p "/" admin '.*' '.*' '.*'  

#查看用户权限
rabbitmqctl list_user_permissions admin
```
## 配置用户远程访问
```bash
#修改配置文件
vi /etc/rabbitmq/rabbitmq.config 

#保存以下内容
[
{rabbit, [{tcp_listeners, [5672]}, {loopback_users, ["test"]}]}
].
```
## 重启服务
`systemctl restart rabbitmq-server`