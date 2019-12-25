---
title: "linux服务器配置"
date: 2019-08-20
description: ""
tags: [linux,vps]
categories: [笔记]
---

### 登录配置
1. 本机生成密钥(输入管理密码)  
`ssh-keygen -t rsa -C "yzlc233@outlook.com"`
2. 把公钥复制到服务器  
`ssh-copy-id -p port user@host`
3. ssh登录  
`ssh -p port user@host`
### 安装工具包
```
yum -y update && yum -y upgrade
yum -y install wget
yum -y install vim
yum -y install zip unzip
```
### vim配置
- `vim /root/.vimrc`
```
#设置tab分隔符为4个空格
set tabstop=4
#解决中文乱码的设置
set termencoding=utf-8
set encoding=prc
```
### 修改时区
`timedatectl set-timezone Asia/Shanghai`
### SS
```
#安装
wget --no-check-certificate -O shadowsocks-all.sh https://raw.githubusercontent.com/teddysun/shadowsocks_install/master/shadowsocks-all.sh && chmod +x shadowsocks-all.sh && ./shadowsocks-all.sh 2>&1 | tee shadowsocks-all.log

#卸载
./shadowsocks-all.sh uninstall

#命令  
/etc/init.d/shadowsocks-python start | stop | restart | status

#配置文件
/etc/shadowsocks-python/config.json
```
### BBR
```
#安装
wget --no-check-certificate https://github.com/teddysun/across/raw/master/bbr.sh && chmod +x bbr.sh && ./bbr.sh

#测试
`sysctl net.ipv4.tcp_available_congestion_control`
```
### mysql
```
#安装
wget 'https://dev.mysql.com/get/mysql57-community-release-el7-11.noarch.rpm'
sudo yum -y install mysql-community-server
systemctl start mysqld
systemctl status mysqld
sudo grep 'temporary password' /var/log/mysqld.log
mysql -uroot -p  #输入查看到的密码

#2. 配置
vim /etc/my.cnf

[client]
default-character-set = utf8
[mysqld]
default-storage-engine = INNODB
character-set-server = utf8
collation-server = utf8_general_ci #不区分大小写
collation-server =  utf8_bin #区分大小写
collation-server = utf8_unicode_ci #比 utf8_general_ci 更准确
```
1. 授权任何主机连接
```
mysql -u 用户名 -p
GRANT ALL PRIVILEGES ON *.- TO '用户名'@'%' IDENTIFIED BY '密码' WITH GRANT OPTION;
FLUSH PRIVILEGES;
```
### git
1. `yum -y install git`
2. `adduser git`
3. 证书登录  
    - 生成公钥  
    `ssh-keygen`
    - 导入  
    `ssh -p port user@host 'mkdir -p .ssh && cat >> /home/git/.ssh/authorized_keys' < ~/.ssh/id_rsa.pub`
4. 添加仓库
`git init --bare sample.git`
5. 仓库授权
`chown -R git:git sample.git`
6. 下载
`git clone ssh://git@server:port/srv/sample.git`
### consul
1. 下载
```
wget https://releases.hashicorp.com/consul/1.4.2/consul_1.4.2_linux_amd64.zip
unzip consul_1.4.2_linux_amd64.zip
```
2. 启动
`./consul agent -dev > consul.log 2>&1 &`
### rabbitmq
1. 下载脚本
```
curl -s https://packagecloud.io/install/repositories/rabbitmq/rabbitmq-server/script.rpm.sh | sudo bash
curl -s https://packagecloud.io/install/repositories/rabbitmq/erlang/script.rpm.sh | sudo bash
```
2. 安装
```
yum  -y install erlang
yum -y install rabbitmq-server
```
3. 命令
```
#启动服务
systemctl start rabbitmq-server

#查看状态
systemctl status rabbitmq-server

#设置为开机启动
systemctl enable rabbitmq-server
```
4. 添加用户并授权
```
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
5. 配置用户远程访问
```
#修改配置文件
vi /etc/rabbitmq/rabbitmq.config 

#保存以下内容
[
{rabbit, [{tcp_listeners, [5672]}, {loopback_users, ["test"]}]}
].
```
 6. 重启服务
`systemctl restart rabbitmq-server`
### redis
1. 安装
```
wget http://download.redis.io/releases/redis-5.0.3.tar.gz
tar xzf redis-5.0.3.tar.gz
cd redis-5.0.3
make
```
2. 配置
```
#修改配置文件
vi ./redis.conf

#daemonize设置为yes
daemonize yes
```
3. 启动
`src/redis-server ./redis.conf`