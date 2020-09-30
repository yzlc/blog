---
title: "mac"
date: 2020-09-28T11:26:33+08:00
tags: [mac]
categories: [教程]
---

## 挂载EFI
```bash
sudo diskutil mount disk0s1
```

## win时间同步
```bat
Reg add HKLM\SYSTEM\CurrentControlSet\Control\TimeZoneInformation /v RealTimeIsUniversal /t REG_DWORD /d 1
```