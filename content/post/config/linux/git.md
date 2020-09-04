---
title: "git - linux"
date: 2019-09-01T10:32:33+08:00
tags: [git]
categories: [配置]
---

## 安装 
`yum -y install git`
## 添加用户 
`adduser git`
## 证书登录  
    - 生成公钥  
    `ssh-keygen`
    - 导入  
    `ssh -p port user@host 'mkdir -p .ssh && cat >> /home/git/.ssh/authorized_keys' < ~/.ssh/id_rsa.pub`
## 添加仓库
`git init --bare sample.git`
## 仓库授权
`chown -R git:git sample.git`
## 下载
`git clone --depth 1 --recursive ssh://git@server:port/srv/sample.git`