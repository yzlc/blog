---
title: "git"
date: 2019-09-01T10:32:33+08:00
tags: [git]
categories: [笔记]
---

## git
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