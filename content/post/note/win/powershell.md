---
title: "powershell"
date: 2020-12-28T15:41:10+08:00
tags: [powershell]
categories: [note]
---

## 代理
```powershell
#设置代理
$Env:https_proxy="socks5://localhost:10808"
#取消代理

#查看代理
$env:https_proxy
```