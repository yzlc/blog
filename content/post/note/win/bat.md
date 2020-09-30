---
title: "bat"
date: 2019-06-30T15:42:10+08:00
tags: [bat]
categories: [笔记]
---

## 变量
- 用户目录：`%UserProfile%`

## 开机启动
新建vbs
```bat
Set ws = CreateObject("Wscript.Shell")
ws.run "cmd /c .\startup.bat",vbhide
```