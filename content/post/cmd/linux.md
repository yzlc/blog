---
title: "linux命令"
date: 2020-01-16T10:32:33+08:00
tags: [linux]
categories: [命令]
---

## 文件/目录
- 复制  
    `cp -r 目录 目录`
- 按文件/目录大小排序  
    `du -sh * | sort -rn | head`
- 实时浏览
  - `tail -f xx`

## 权限
- 授权  
    `sudo chmod 755 *.sh`

## 远程
- 上传
  - `scp file root@ip:/file`
- 下载
  - `scp root@ip:/file file`

## 进程
- 查找
  - `ps -ef|grep consul`
- 执行
  - `nohup xx &`
