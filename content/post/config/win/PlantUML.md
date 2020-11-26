---
title: "PlantUML"
date: 2020-11-26T14:15:33+08:00
tags: [PlantUML]
categories: [配置]
---

## 安装
- vscode安装`PlantUML`插件
- 安装[Graphviz](https://www2.graphviz.org/Packages/stable/windows/10/cmake/Release/x64/graphviz-install-2.44.1-win64.exe)

## 绘图
1. 编写脚本：新建md文件
   ```md
   @startuml

   @enduml
   ```
2. 查看图像：快捷键 `Alt + D` （`Ctrl+Shift+P` -> `PlantUML` -> `Preview Current Diagram`）

3. `Ctrl + Shift + P` -> `Export Current Diagram`导出

## 流程图
```md
@startuml

start
:步骤1处理;
:步骤2处理;
if (条件1判断) then (true)
    :条件1成立时执行的动作;
    if (分支条件2判断) then (no)
        :条件2不成立时执行的动作;
    else
        if (条件3判断) then (yes)
            :条件3成立时的动作;
        else (no)
            :条件3不成立时的动作;
        endif
    endif
    :顺序步骤3处理;
endif

if (条件4判断) then (yes)
:条件4成立的动作;
else
    if (条件5判断) then (yes)
        :条件5成立时的动作;
    else (no)
        :条件5不成立时的动作;
    endif
endif
stop

@enduml
```