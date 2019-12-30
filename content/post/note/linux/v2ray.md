---
title: "v2ray"
date: 2019-09-17
description: "centos7安装v2ray"
keywords: [v2ray,namesilo,cloudflare,centos7]
tags: [v2ray,namesilo,cloudflare]
categories: [笔记]
---

## 注册域名[namesilo](https://www.namesilo.com/)
1. unlock Domains
2. change nameservers：修改为cloudflare域名服务器
## 解析ip [cloudflare](https://www.cloudflare.com)
### add a site
### DNS
1. 添加A记录，输入vps的ip
2. `status`点击为`dns only`状态（不然caddy不能自动创建tls加密）
- ip被封处理（vps操作完之后）
1. `status`点亮
2. `crypto`处把ssl状态改为full
## vps操作
### install new os：centos7 64
### 安装bbr
    yum install wget net-tools -y
    wget --no-check-certificate https://github.com/teddysun/across/raw/master/bbr.sh && chmod +x bbr.sh && ./bbr.sh
### caddy
1. `wget -N --no-check-certificate https://raw.githubusercontent.com/ToyoDAdoubi/doubi/master/caddy_install.sh && chmod +x caddy_install.sh && bash caddy_install.sh install`
2. 编辑配置文件：`vi /usr/local/caddy/Caddyfile`

        www.xxx.com
        {
        log ./caddy.log
        proxy /ws localhost:10000 {
            websocket
            header_upstream -Origin
        }
        }
3. 启动：`/etc/init.d/caddy start`
### v2ray
1. 安装：`bash <(curl -L -s https://install.direct/go.sh)`
2. 编辑配置：`vi /etc/v2ray/config.json`

        {
        "inbounds": [
            {
            "port": 10000,
            "listen":"127.0.0.1",
            "protocol": "vmess",
            "settings": {
                "clients": [
                {
                    "id": "id",
                    "alterId": 64
                }
                ]
            },
            "streamSettings": {
                "network": "ws",
                "wsSettings": {
                "path": "/ws"
                }
            }
            }
        ],
        "outbounds": [
            {
            "protocol": "freedom",
            "settings": {}
            }
        ]
        }
- service v2ray start
- service v2ray status