---
title: "maven"
date: 2019-03-05T10:32:33+08:00
tags: [maven]
categories: [配置]
---

settings.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
  
  <localRepository>C:\repository</localRepository>

  <mirrors>
    <mirror>
          <id>aliyun</id>
          <mirrorOf>central</mirrorOf>
          <name>Nexus aliyun</name>
          <url>http://maven.aliyun.com/nexus/content/groups/public</url>
    </mirror>
  </mirrors>
</settings>
```

脚本
```bat
mkdir %USERPROFILE%\.m2
copy .\settings.xml %USERPROFILE%\.m2\
```