---
title: "excel"
date: 2020-04-27T10:32:33+08:00
tags: [excel]
categories: [笔记]
---

### 列出所有工作表名称
1. `公式`->`名称管理器`
2. `新建`
   * 名称: 
    `Sheets`
   * 引用位置: `=GET.WORKBOOK(1)&T(NOW())`
3. 在空白单元格中键入以下公式，将自动填充手柄拖动到其他单元格
   `=LOOKUP("xxxxx",CHOOSE({1,2},"",INDEX(MID(Sheets,FIND("]",Sheets)+1,255),ROWS(A$1:A1))))`