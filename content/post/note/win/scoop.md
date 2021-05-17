---
title: "scoop"
date: 2020-12-31T17:16:10+08:00
tags: [scoop]
categories: [note]
---

## script
```powershell
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser

#$env:SCOOP='C:\Scoop'
# 先添加用户级别的环境变量 SCOOP
#[environment]::setEnvironmentVariable('SCOOP',$env:SCOOP,'User')

#打开代理pac模式

iwr -useb get.scoop.sh | iex

scoop install 7zip
scoop install aria2
scoop config aria2-enabled false
scoop install git
#scoop install hugo-extended
#scoop install nodejs

scoop bucket add yzlc https://github.com/yzlc/scoop
scoop install aria2Conf
scoop install tomcat7
#scoop install battle
#scoop install fortiClient

scoop bucket add kkzzhizhou https://github.com/kkzzhizhou/scoop-zapps
scoop install wechat
#scoop install Charles
#scoop install DingTalk

scoop bucket add extras
scoop install vscode
scoop install sumatrapdf
scoop install mobaXterm
scoop install tortoisesvn
scoop install idea-ultimate
scoop install anki

scoop bucket add java
scoop install openjdk8-redhat
```