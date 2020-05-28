---
title: "windows"
date: 2019-08-20T15:42:10+08:00
tags: [windows]
categories: [配置]
---

## 设置
- 重命名这台电脑
- 桌面图标设置
- 后台应用
- Cortana：关闭后台
- 性能选项：自定义
   - [x] 平滑屏幕字体边缘
   - [x] 在窗口下显示阴影
- [ ] 使用游戏栏录制
- [ ] 提高指针精确度
- [ ] 允许远程
- [ ] 碎片整理
- [ ] 创建还原点
## windowsDefender  
病毒和威胁防护-病毒和威胁防护设置-排除项-添加文件夹
## 禁用服务
- Windows Search
- Connected User Experiences and Telemetry
- Background Intelligent Transfer Service
- 第三方软件服务  
msconfig – 服务 – 隐藏所有的Microsoft服务 - 关闭不需要的服务
## 子系统
### 换源
[中科大源](http://mirrors.ustc.edu.cn/help/ubuntu.html) [清华源](https://mirrors.tuna.tsinghua.edu.cn/help/ubuntu/) [阿里源](https://developer.aliyun.com/mirror/)
```bash
# 源备份
sudo mv /etc/apt/sources.list /etc/apt/sources.list.bak

# 新源
sudo vim /etc/apt/sources.list

# 执行系统更新命令
sudo apt-get update
```