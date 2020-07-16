---
title: "jdk8绿色版"
date: 2020-07-16T08:42:33+08:00
tags: [jdk8]
categories: [配置]
---

提取文件  
`jdk-8u221-windows-x64\.rsrc\1033\JAVA_CAB10\111\tools.zip`

解压`tools.zip`，执行  
`for /r %x in (*.pack) do .\bin\unpack200 -r "%x" "%~dx%~px%~nx.jar"`