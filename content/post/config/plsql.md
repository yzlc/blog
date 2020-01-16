---
title: "plsql"
date: 2020-01-10T10:54:57+08:00
tags: [plsql]
categories: [配置]
---

## Preferences
### Oracle
- Connection
  - Oracle Home  
    `C:\Users\yzlc2\soft\instantclient_11_2`
  - OCI library  
    `C:\Users\yzlc2\soft\instantclient_11_2\oci.dll`
- Logon History
  - [x] Store with password
- Other
  - Updates
    - [x] Never
  - News
    - [x] Never

## 乱码
1. 查询编码  
`select userenv('language') from dual;`
2. 设置环境变量
   - `NLS_LANG`
   - 步骤1的编码
3. 重启plsql