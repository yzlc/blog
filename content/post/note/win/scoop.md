---
title: "scoop"
date: 2020-12-31T17:16:10+08:00
tags: [scoop]
categories: [笔记]
---

## 脚本
```powershell
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser

$env:SCOOP='C:\Scoop'
# 先添加用户级别的环境变量 SCOOP
[environment]::setEnvironmentVariable('SCOOP',$env:SCOOP,'User')

iwr -useb get.scoop.sh | iex

scoop install aria2
scoop install git

scoop bucket add extras
scoop install vscode
scoop install SumatraPDF
scoop install MobaXterm

scoop bucket add devbox https://github.com/dennislloydjr/scoop-bucket-devbox.git
scoop install tomcat7


scoop bucket add java
scoop install oraclejdk8u

scoop install idea-ultimate
#社区版
#scoop install idea

scoop install svn
scoop install battle

scoop bucket add dorado https://github.com/chawyehsu/dorado
scoop install dorado/wechat
```