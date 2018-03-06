package com.cindychen.arithmetic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * created by cindy
 */
public class ArithmeticActivity extends AppCompatActivity {

    private TreeNode mTreeNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* TreeNode treeNode = layNode(mTreeNode);
        printNode(treeNode);*/
        Log.d("chen", String.valueOf(aIncludeB("aaaabbeeer", "aaabeeer")));

    }


    /**
     * 求 1+2+3+……+n
     * 短路求值 + 递归
     */
    private int factorial(int n) {
        int sum = n;
        boolean result = (n > 0) && ((sum += factorial(n - 1)) > 0);
        return sum;
    }


    /**
     * 判断 a 字符串是否是 b 字符串的超集
     */
    private boolean aIncludeB(String a, String b) {
        if (a.equals(b)) {
            return true;
        }
        if (TextUtils.isEmpty(a) || TextUtils.isEmpty(b)) {
            return false;
        }
        char[] aChar = a.toCharArray();
        char[] bChar = b.toCharArray();

        HashMap<Character, Integer> aMap = new HashMap<>();
        HashMap<Character, Integer> bMap = new HashMap<>();
        // 循环a
        for (int i = 0; i < aChar.length; i++) {
            char temp = aChar[i];
            if (aMap.containsKey(temp)) {
                aMap.put(temp, aMap.get(temp) + 1);
            } else {
                aMap.put(temp, 1);
            }
        }
        // 循环b
        for (int i = 0; i < bChar.length; i++) {
            char temp = bChar[i];
            if (bMap.containsKey(temp)) {
                bMap.put(temp, bMap.get(temp) + 1);
            } else {
                bMap.put(temp, 1);
            }
        }
        // 遍历 bMap
        Set<Map.Entry<Character, Integer>> entries = bMap.entrySet();
        for (Map.Entry<Character, Integer> map : entries) {
            Character key = map.getKey();
            // 如果 aMap元素 包含 bMap元素
            if (aMap.containsKey(key)) {
                // aMap元素个数 小于 bMap元素个数
                if (aMap.get(key) < map.getValue()) {
                    return false;
                }
            } else { //aMap元素 不包含 bMap元素
                return false;
            }
        }
        return true;
    }

    /**
     * 构建二叉树
     * root
     * /   \
     * a     b
     * / \   /
     * c   d e
     */
    private void buildTree() {
        TreeNode c = new TreeNode();
        c.left = null;
        c.right = null;
        c.value = "c";
        TreeNode d = new TreeNode();
        d.left = null;
        d.right = null;
        d.value = "d";
        TreeNode a = new TreeNode();
        a.left = c;
        a.right = d;
        a.value = "a";
        TreeNode e = new TreeNode();
        e.left = null;
        e.right = null;
        e.value = "e";
        TreeNode b = new TreeNode();
        b.left = e;
        b.right = null;
        b.value = "b";
        mTreeNode = new TreeNode();
        mTreeNode.left = a;
        mTreeNode.right = b;
        mTreeNode.value = "root";
    }

    /**
     * 构建纯右子数
     */
    private TreeNode buildRightTree() {
        TreeNode d = new TreeNode();
        d.left = null;
        d.right = null;
        d.value = "d";
        TreeNode c = new TreeNode();
        c.left = null;
        c.right = d;
        c.value = "c";
        TreeNode b = new TreeNode();
        b.left = null;
        b.right = c;
        b.value = "b";
        TreeNode a = new TreeNode();
        a.left = null;
        a.right = b;
        a.value = "a";
        TreeNode root = new TreeNode();
        root.left = null;
        root.right = a;
        root.value = "root";
        return root;
    }


    /**
     * 二叉树 中序排序(搜索)
     * 深度优先遍历-递归
     */
    private void printNode(TreeNode treeNode) {
        // baseCase
        if (treeNode == null) {
            return;
        }
        printNode(treeNode.left);
        Log.d("node", "root:" + treeNode.value);
        printNode(treeNode.right);

    }

    /**
     * 广度优先遍历
     * 广度优先-队列
     */
    private void guangduPrintNode(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(treeNode);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            Log.d("node:", "poll-" + poll.value);
            TreeNode left = poll.left;
            TreeNode right = poll.right;
            if (left != null) {
                queue.add(left);
            }
            if (right != null) {
                queue.add(right);
            }
        }
    }

    /**
     * 翻转二叉树,将 treeNode 直接翻转
     */
    private void revaseNode(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        revaseNode(treeNode.left);
        revaseNode(treeNode.right);
        TreeNode temp = treeNode.left;
        treeNode.left = treeNode.right;
        treeNode.right = temp;
    }

    /**
     * 平铺二叉树 只考虑 左子树，因为右子树经过循环遍历后已经平铺
     */
    private TreeNode layNode(TreeNode treeNode) {
        if (treeNode == null) {
            return null;
        }
        TreeNode left = layNode(treeNode.left);
        TreeNode right = layNode(treeNode.right);
        if (left == null) {
            treeNode.right = right;
            return treeNode;
        }
        // left 不为空
        treeNode.right = left;
        TreeNode lastLeft = left;
        while (lastLeft.right != null) {
            lastLeft = lastLeft.right;
        }
        lastLeft.right = right;

        return treeNode;
    }



/*    public TreeNode revaseNode(TreeNode root){
        //先处理base case，当root ==null 时，什么都不需要做,返回空指针
        if(root == null){
            return null;
        }
        else{
            //把左子树翻转
            TreeNode left = revaseNode(root.left);
            //把右子树翻转
            TreeNode right = revaseNode(root.right);
            //把左右子树分别赋值给root节点，但是是翻转过来的顺序
            root.left = right;
            root.right = left;
            //返回根节点
            return root;
        }
    }*/


    private class TreeNode {
        TreeNode left;
        TreeNode right;
        String value;
    }

}
