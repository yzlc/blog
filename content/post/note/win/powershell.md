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

## 环境变量
```powershell
[environment]::SetEnvironmentvariable("GOPATH", "$env:USERPROFILE\gopath", "User")
#调用命令结果：$(命令)
#获取原有用户 PATH 变量：$([environment]::GetEnvironmentvariable("Path", "User"))
#注意 PATH 中条目以分号结尾
[environment]::SetEnvironmentvariable("PATH", "$([environment]::GetEnvironmentvariable("Path", "User"));%GOPATH%\bin", "User")

[environment]::SetEnvironmentvariable("GOROOT", "C:\go", "Machine")
#调用命令结果：$(命令)
#获取原有系统 PATH 变量：$([environment]::GetEnvironmentvariable("Path", "Machine"))
[environment]::SetEnvironmentvariable("PATH", "$([environment]::GetEnvironmentvariable("Path", "Machine"));%GOROOT%\bin", "Machine")
```