---
title: "bat"
date: 2019-06-30T15:42:10+08:00
tags: [bat]
categories: [笔记]
---

## 变量
- 用户目录：`%UserProfile%`

## 管理员
```bat
@echo off&(cd/d "%~dp0")&(cacls "%SystemDrive%\System Volume Information" >nul 2>&1)||(start "" mshta vbscript:CreateObject^("Shell.Application"^).ShellExecute^("%~snx0"," %*","","runas",1^)^(window.close^)&exit /b)
```

## 开机启动
新建vbs
```bat
Set ws = CreateObject("Wscript.Shell")
ws.run "cmd /c .\startup.bat",vbhide
```