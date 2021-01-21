---
title: "bat"
date: 2019-06-30T15:42:10+08:00
tags: [bat]
categories: [笔记]
---

## 变量
- 用户目录：`%UserProfile%`
- 开机启动（用户）：`%APPDATA%\Microsoft\Windows\Start Menu\Programs\Startup`
- 开机启动：`%ALLUSERSPROFILE%\Microsoft\Windows\Start Menu\Programs\Startup`

## 删目录、文件
```bat
rd /s/q dir
del /s/q file
```

## 复制目录
```bat
xcopy src dest\src /S /E /y /q
```

## 日期
```bat
set year=%date:~0,4%
set month=%date:~5,2%
set day=%date:~8,2%
set date=%year%%month%%day%
```

## utf8
```bat
chcp 65001
```

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

## 启动
```bat
start "test" java -jar -Dfile.encoding=utf8 test.jar
```

## 停止
```bat
TASKKILL /FI "WINDOWTITLE eq test"
```

## 代理
```bat
set https_proxy=socks5://localhost:10808
echo %https_proxy%
set https_proxy=
```