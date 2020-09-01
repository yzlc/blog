---
title: "windows修改服务错误"
date: 2020-08-31T08:39:33+08:00
tags: [windows]
categories: [教程]
---

## 注册表修改
1. 打开`服务`，记下`服务名称`
2. 打开`注册表`，定位到`HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services`，找到`服务名称`的项
3. 修改`Start`值为`3`（手动）
4. 如果修改报错，右键`服务名称`修改`权限`