---
title: "maven"
date: 2019-03-05T10:32:33+08:00
tags: [maven]
categories: [配置]
---

## settings.xml
[一键配置脚本](/files/note/maven.zip)
- 仓库
    ```xml
    <mirror>
        <id>aliyun</id>
        <mirrorOf>central</mirrorOf>
        <name>Nexus aliyun</name>
        <url>http://maven.aliyun.com/nexus/content/groups/public</url>
    </mirror>
    ```
- 脚本
    ```bat
    mkdir %USERPROFILE%\.m2
    copy .\settings.xml %USERPROFILE%\.m2\
    ```