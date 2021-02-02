---
title: "leetcode初级算法"
date: 2020-09-10T10:52:33+08:00
tags: [算法]
categories: [note]
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
### 环形链表
>龟兔赛跑解法
```java
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null||head.next == null) return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while(slow!=fast){
            if(fast==null||fast.next==null)
                return false;
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}
```

## 树
### [验证二叉搜索树](https://segmentfault.com/a/1190000022041701)
>left(父节点左边界,父节点的值) right(父节点的值,父节点右边界)
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public static boolean isValidBST(TreeNode node,Integer lower,Integer upper) {
        if (node==null)return true;
        int val = node.val;
        if (lower!=null&&val<=lower)return false;
        if (upper!=null&&val>=upper)return false;
        if (!isValidBST(node.left,lower,val))return false;
        if (!isValidBST(node.right,val,upper))return false;
        return true;
    }

    public static boolean isValidBST(TreeNode root) {
        return isValidBST(root,null,null);
    }
}
```
### 二叉树的层序遍历
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) return ret;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            ret.add(level);
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                level.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return ret;
    }
}
```
### 将有序数组转换为二叉搜索树
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode sortedArrayToBST(int[] nums, int left, int right) {
        if (left > right) return null;
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, left, mid - 1);
        root.right = sortedArrayToBST(nums, mid + 1, right);
        return root;
    }
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums,0,nums.length-1);
    }
}
```