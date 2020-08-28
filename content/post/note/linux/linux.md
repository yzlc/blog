---
title: "linux"
date: 2019-06-01T10:32:33+08:00
tags: [linux]
categories: [笔记]
---

## 查找
`find / -type d -name *tomcat* 2>/dev/null`

## 按文件/目录大小排序  
`du -sh * | sort -rn | head`

## 查看进程运行的完整路径
`ll /proc/PID`

## 后台运行
`nohup xx >/dev/null 2>&1 &`

## 脚本
```bash
#!/bin/bash
day=`date +%Y%m%d`
echo '日期：'$day
```

## 定时任务
```bash
crontab -e #编辑 分 时 日 月 星期 命令
crontab -l #查看
```

## [命令大全](http://man.linuxde.net/)
日期 `date -d '1 day ago' +%Y%m%d`

日历 `cal`

磁盘空间 `df`

空闲内存 `free`

## 压缩
`zip file.zip file`

`unzip file.zip`

## 代理下载
`curl -fLO --socks5 127.0.0.1:10808 url`