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
#scoop install tomcat7
#scoop install tomcat9
#scoop install battle
#scoop install fortiClient
#scoop install plsql
#scoop install oracleClient
#scoop install tim
#scoop install wechat
#scoop install lister
#scoop install oraclejdk7
#scoop install oraclejdk8
#scoop install Charles
#scoop install DingTalk

scoop bucket add extras
scoop install vscode
scoop install sumatrapdf
scoop install mobaXterm
scoop install tortoisesvn
scoop install idea-ultimate
scoop install anki
#scoop install potplayer
#scoop install steam
#scoop install rufus

#scoop bucket add java
#scoop install openjdk8-redhat
#scoop install openjdk7-unofficial
```