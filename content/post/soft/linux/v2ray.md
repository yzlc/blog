---
title: "v2ray"
date: 2019-09-16T10:32:33+08:00
tags: [v2ray]
categories: [笔记]
---

## [namesilo](https://www.namesilo.com)
1. unlock Domains
2. change nameservers

## [cloudflare](https://www.cloudflare.com)
1. Add a Site
2. DNS
   - Add record
     - Type：`A`
     - Name：`www` / `@`
     - Content：ip
     - Proxy Status：`DNS only`（vps操作完成后改为`Proxied`）
3. SSL
   - encryption mode：`Full`

## caddy
1. `wget -N --no-check-certificate https://raw.githubusercontent.com/ToyoDAdoubi/doubi/master/caddy_install.sh && chmod +x caddy_install.sh && bash caddy_install.sh install`
2. 编辑配置文件  
`vi /usr/local/caddy/Caddyfile`
    ```json
    yzlc.xyz, www.yzlc.xyz
    {
        gzip
        root /var/www
        log ./caddy.log
        proxy /ws localhost:10000 {
            websocket
            header_upstream -Origin
        }
    }
    ```
- 命令：  
`/etc/init.d/caddy start`

## v2ray
1. 安装  
`bash <(curl -L -s https://install.direct/go.sh)`
2. 编辑配置  
`vi /etc/v2ray/config.json`
    ```json
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
    ```
- 命令
    - `service v2ray start`
    - `service v2ray status`