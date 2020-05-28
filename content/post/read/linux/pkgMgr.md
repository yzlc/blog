---
title: "linux - 软件包管理"
date: 2019-06-01T10:32:33+08:00
tags: [linux]
categories: [阅读]
---

### 打包系统
- Debian Style (.deb)	Debian, Ubuntu, Xandros, Linspire
- Red Hat Style (.rpm)	Fedora, CentOS, Red Hat Enterprise Linux, OpenSUSE, Mandriva, PCLinuxOS

### 包管理工具
| 发行版                                   | 底层工具 | 上层工具          |
| ---------------------------------------- | -------- | ----------------- |
| Debian-Style                             | dpkg     | apt-get, aptitude |
| Fedora, Red Hat Enterprise Linux, CentOS | rpm      | yum               |
- 底层工具用来处理这些任务，比方说安装和删除软件包文件
- 上层工具，完成元数据搜索和依赖解析

### 查找软件
- Debian	`apt-get update; apt-cache search search_string`
- Red Hat	`yum search search_string`

### 安装软件（资源库）
- Debian	`apt-get update; apt-get install package_name`
- Red Hat	`yum install package_name`

### 安装软件（软件包）
- Debian	`dpkg --install package_file`
- Red Hat	`rpm -i package_file`

### 卸载软件
- Debian	`apt-get remove package_name`
- Red Hat	`yum erase package_name`

### 更新软件（资源库）
- Debian	`apt-get update; apt-get upgrade`
- Red Hat	`yum update`

### 更新软件（软件包）
- Debian	`dpkg --install package_file`
- Red Hat	`rpm -U package_file`

### 列出安装的软件包
- Debian	`dpkg --list`
- Red Hat	`rpm -qa`
### 确定是否安装了一个软件包
- Debian	`dpkg --status package_name`
- Red Hat	`rpm -q package_name`
### 显示所安装软件包的信息
- Debian	`apt-cache show package_name`
- Red Hat	`yum info package_name`
### 查找安装了某个文件的软件包
- Debian	`dpkg --search file_name`
- Red Hat	`rpm -qf file_name`