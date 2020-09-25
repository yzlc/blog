---
title: "leetcode初级算法"
date: 2020-09-10T10:52:33+08:00
tags: [算法]
categories: [笔记]
---

## 数组
### 旋转图像
>将矩阵划分为4部分旋转

![](/images/note/leetcode/rectangles.png)

| ij   | 交换轨迹                                           |
| ---- | -------------------------------------------------- |
| 00   | 00->30->33->03                                     |
| 01   | 01->20->32->13                                     |
| 10   | 10->31->23->02                                     |
| 11   | 11->21->22->12                                     |
| 坐标 | (i,j)->(len-1-j,i)->(len-1-i,len-1-j)->(j,len-1-i) |

```java
public void rotate(int[][] matrix){
    int len = matrix.length;
    for (int i = 0; i < (len + 1) / 2; i++) {
        for (int j = 0; j < len / 2; j++) {
            int tmp = matrix[i][j];
            matrix[i][j] = matrix[len - 1 - j][i];
            matrix[len - 1 - j][i] = matrix[len - 1 - i][len - 1 - j];
            matrix[len - 1 - i][len - 1 - j] = matrix[j][len - 1 - i];
            matrix[j][len - 1 - i] = tmp;
        }
    }
}
```

## 字符串
### 整数反转
```java
public int reverse(int x) {
    int rev = 0;
    while (x != 0) {
        int pop = x % 10;
        x /= 10;
        if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > Integer.MAX_VALUE % 10))
            return 0;
        if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < Integer.MIN_VALUE % 10))
            return 0;
        rev = rev * 10 + pop;
    }
    return rev;
}
```

## 链表
### 反转链表
```java
public ListNode reverse(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;
    while (curr != null) {
        ListNode next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
    }
    return prev;
}
```