---
title: "Padavan"
date: 2019-10-08
description: Padavan安装v2ray
keywords : ["Padavan安装v2ray"]
tags: [Padavan]
categories: [笔记]
---

[Padavan](https://www.right.com.cn/forum/thread-161324-1-1.html)
## [v2ray](https://sixu.life/padavan-v2ray.html)
### 导出节点
1. 在电脑上将正常的节点导出为客户端json
2. 修改json第10行"listen"为路由器的管理地址
### v2ray设置
- 搭建Web环境==>v2ray==>v2ray proxy 开关 打开==>粘贴v2ray_config_script==>应用本页设置
### transocks设置
- 广告屏蔽功能==>transocks==>transocks 开关 打开
- 透明重定向的代理服务器IP、域名:路由器的管理IP
- 透明重定向的代理服务器端口:json文件第9行的端口
- 远端服务器IP、域名:v2ray节点的域名或者IP
