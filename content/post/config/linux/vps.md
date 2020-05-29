---
title: "vps"
date: 2019-09-01T10:32:33+08:00
tags: [vps]
categories: [配置]
---

### 登录配置
1. 本机生成密钥(输入管理密码)  
`ssh-keygen -t rsa -C "yzlc233@outlook.com"`
2. 把公钥复制到服务器  
`ssh-copy-id -p port user@host`
3. ssh登录  
`ssh -p port user@host`

### 安装工具包
```bash
yum -y update && yum -y upgrade
yum -y install wget
yum -y install vim
yum -y install zip unzip
```

### vim配置
- `vim /root/.vimrc`
```bash
#设置tab分隔符为4个空格
set tabstop=4
#解决中文乱码的设置
set termencoding=utf-8
set encoding=prc
```

### 修改时区
`timedatectl set-timezone Asia/Shanghai`