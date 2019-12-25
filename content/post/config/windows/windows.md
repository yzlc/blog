---
title: "Windows环境配置"
date: 2019-08-20
description: ""
tags: [Windows]
categories: [配置]
---

## git([菜鸟教程](http://www.runoob.com/w3cnote/git-guide.html))
### 免密
#### ssh
`ssh-keygen -t rsa -C "yzlc@email.com"`
#### http
```
#1. 新增配置文件
cd ~
vim .git-credentials

#2. 添加内容
https://username:password@github.com
http://username:password@github.com

#3. 配置
git config --global credential.helper store
```
### proxy
```
git config --global http.proxy socks5://127.0.0.1:1080
git config --global https.proxy socks5://127.0.0.1:1080

#下载文件
curl -fLO --socks5 127.0.0.1:10808 url
```
## 优化
1. 磁盘碎片整理
2. windowsDefender全盘扫描一次系统
3. 关闭计划任务里的隐藏的自启动程序：控制面板 – 所有控制面板项 – 管理工具 – 任务计划程序 – 任务计划程序库 – 右侧任务列表 – 禁用不需要的任务。
4. 关闭讨厌的第三方软件服务项：Win+ R输入“msconfig” – 确定，运行系统配置 – 勾选隐藏所有的Microsoft服务去掉不需要执行的服务
5. 启用或关闭Windows功能
6. 创建还原点
7. 性能选项：自定义
	- 平滑屏幕字体边缘
	- 启用速览
	- 显示缩略图，而不是显示图标
	- 在桌面上为图标标签使用阴影
	- 在最大化和最小化时显示窗口动画
## nginx
1. 启动
`start nginx`
3. 正常停止
`nginx -s quit`
3. 快速停止
`nginx -s stop`
## bat
### 变量
- 用户目录：`%UserProfile%`
