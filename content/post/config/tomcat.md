---
title: "tomcat"
date: 2019-12-25T09:08:26+08:00
tags: [tomcat]
categories: [配置]
---

## 编码问题
- bin/catalina.bat  
加入`SET CATALINA_OPTS=-Dfile.encoding=UTF-8`
- conf/server.xml  
在`<Connector port="8080"...`中加入` URIEncoding="UTF-8"`