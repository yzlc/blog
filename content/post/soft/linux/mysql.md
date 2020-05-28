---
title: "mysql"
date: 2019-09-01T10:32:33+08:00
tags: [mysql]
categories: [笔记]
---

## mysql
1. 安装
```bash
wget 'https://dev.mysql.com/get/mysql57-community-release-el7-11.noarch.rpm'
sudo yum -y install mysql-community-server
systemctl start mysqld
systemctl status mysqld
sudo grep 'temporary password' /var/log/mysqld.log
mysql -uroot -p  #输入查看到的密码
```

2. 配置
- `vim /etc/my.cnf`
    ```
    [client]
    default-character-set = utf8
    [mysqld]
    default-storage-engine = INNODB
    character-set-server = utf8
    collation-server = utf8_general_ci #不区分大小写
    collation-server =  utf8_bin #区分大小写
    collation-server = utf8_unicode_ci #比 utf8_general_ci 更准确
    ```
3. 授权任何主机连接
```bash
mysql -u 用户名 -p
GRANT ALL PRIVILEGES ON *.- TO '用户名'@'%' IDENTIFIED BY '密码' WITH GRANT OPTION;
FLUSH PRIVILEGES;
```