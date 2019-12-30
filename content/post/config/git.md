---
title: "Git"
date: 2019-12-30T15:19:25+08:00
keywords: [git配置]
description: ""
tags: [git]
categories: [配置]
---

## 免密登录
* ssh方式  
`ssh-keygen -t rsa -C "yzlc233@outlook.com"`
* http方式
  ```bash
  #1. 新增配置文件
  cd ~
  vim .git-credentials

  #2. 添加内容
  https://username:password@github.com
  http://username:password@github.com

  #3. 配置账号
  git config --global credential.helper store
  ```

## git全局代理
```bash
git config --global http.proxy socks5://127.0.0.1:10808
git config --global https.proxy socks5://127.0.0.1:10808
```

## 使用代理下载文件
`curl -fLO --socks5 127.0.0.1:10808 url`
