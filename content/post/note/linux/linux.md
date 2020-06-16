---
title: "linux"
date: 2019-06-01T10:32:33+08:00
tags: [linux]
categories: [笔记]
---

## 按文件/目录大小排序  
`du -sh * | sort -rn | head`

## 查看进程运行的完整路径
`ll /proc/PID`

## 后台运行 
`nohup xx &`

## [命令大全](http://man.linuxde.net/)
日期 `date`

日历 `cal`

磁盘空间 `df`

空闲内存 `free`

## 压缩
`zip file.zip file`

`unzip file.zip`

## 代理下载
`curl -fLO --socks5 127.0.0.1:10808 url`