---
title: "ss"
date: 2019-09-01T10:32:33+08:00
tags: [ss]
categories: [笔记]
---

## SS
```bash
#安装
wget --no-check-certificate -O shadowsocks-all.sh https://raw.githubusercontent.com/teddysun/shadowsocks_install/master/shadowsocks-all.sh && chmod +x shadowsocks-all.sh && ./shadowsocks-all.sh 2>&1 | tee shadowsocks-all.log

#卸载
./shadowsocks-all.sh uninstall

#命令  
/etc/init.d/shadowsocks-python start | stop | restart | status

#配置文件
/etc/shadowsocks-python/config.json
```

## BBR
```bash
#安装
wget --no-check-certificate https://github.com/teddysun/across/raw/master/bbr.sh && chmod +x bbr.sh && ./bbr.sh

#测试
`sysctl net.ipv4.tcp_available_congestion_control`
```