---
title: "scoop"
date: 2020-12-31T17:16:10+08:00
tags: [scoop]
categories: [note]
---

## script
```powershell
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser

$env:SCOOP='C:\Scoop'
# 先添加用户级别的环境变量 SCOOP
[environment]::setEnvironmentVariable('SCOOP',$env:SCOOP,'User')

#打开代理pac模式

iwr -useb get.scoop.sh | iex

scoop install 7zip
scoop install aria2
scoop config aria2-enabled false
scoop install git
#scoop install nodejs
#scoop install hugo-extended

scoop bucket add extras
scoop install vscode
scoop install mobaXterm
scoop install tortoisesvn
scoop install idea-ultimate
scoop install sumatrapdf
#scoop install anki
#scoop install potplayer
#scoop install steam

scoop bucket add yzlc https://github.com/yzlc/scoop
scoop install aria2Conf
#scoop install oraclejdk8
#scoop install tomcat7
#scoop install fortiClient
#scoop install Charles
#scoop install tomcat9
#scoop install battle
#scoop install plsql
#scoop install oracleClient
#scoop install tim
#scoop install wechat
#scoop install lister
#scoop install oraclejdk7
#scoop install DingTalk
```