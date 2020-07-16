---
title: "plsql"
date: 2020-01-10T10:54:57+08:00
tags: [plsql]
categories: [配置]
---

## 下载
[plsql](http://www.allroundautomations.com/files/plsqldev1306x64.msi)&emsp;[instantclient-basic-11.2.0.4](https://www.oracle.com/database/technologies/instant-client/winx64-64-downloads.html)

## 注册
product code：  
`4vkjwhfeh3ufnqnmpr9brvcuyujrx3n3le`

serial Number：  
`226959`

password:  
`xs374ca`

## Preferences
### Oracle
Connection
- Oracle Home  
  `C:\soft\instantclient_11_2`
- OCI library  
  `C:\soft\instantclient_11_2\oci.dll`

Logon History
- [x] Store with password

Other
- Updates
  - [x] Never
- News
  - [x] Never

## 乱码
查询编码  
`select userenv('language') from dual;`

设置环境变量
- `NLS_LANG`
- `SIMPLIFIED CHINESE_CHINA.ZHS16GBK`

重启plsql