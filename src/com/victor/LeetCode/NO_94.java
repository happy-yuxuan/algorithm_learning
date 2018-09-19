package com.victor.LeetCode;

import java.util.ArrayList;
import java.util.List;

public class NO_94 {


 //Definition for a binary tree node.
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    /**
     * 使用递归的思想 树的中序遍历
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inorderList = new ArrayList<>();
        if (root == null) {
            return inorderList;
        }

        inorderList.addAll(inorderTraversal(root.left));
        inorderList.add(root.val);
        inorderList.addAll(inorderTraversal(root.right));
        return inorderList;
    }

}
