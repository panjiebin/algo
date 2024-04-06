package com.code.algo.tree;

import java.util.*;

/**
 * 二叉树遍历
 * <p>前、中、后序，表示的是节点与它的左右子节点在遍历中的打印顺序
 * <ul>
 *     <li>前序遍历：自己 -> 左子 -> 右子 </li>
 *     <li>中序遍历：左子 -> 自己 -> 右子 </li>
 *     <li>后序遍历：左子 -> 右子 -> 自己 </li>
 * </ul>
 * @author panjb
 */
public class TreeSearcher {

    private TreeSearcher() {
    }

    public static void main(String[] args) {
        /*
        *          A
        *        /   \
        *       B     C
        *      / \   / \
        *     D   E F   G
        */
        TreeNode<String> d = new TreeNode<>("D");
        TreeNode<String> e = new TreeNode<>("E");
        TreeNode<String> f = new TreeNode<>("F");
        TreeNode<String> g = new TreeNode<>("G");
        TreeNode<String> b = new TreeNode<>("B", d, e);
        TreeNode<String> c = new TreeNode<>("C", f, g);
        TreeNode<String> root = new TreeNode<>("A", b, c);
        System.out.println("前序遍历");
        preOrder(root);
        System.out.println();
        System.out.println("中序遍历");
        inOrder(root);
        System.out.println();
        System.out.println("后序遍历");
        // D, B, E, A, F, C, G
        postOrder(root);
        System.out.println();
        System.out.println("按层遍历");
        System.out.println(floorLevelTree(root));
    }

    /**
     * 前序遍历
     * @param node 树节点
     * @param <T> 类型
     */
    public static <T> void preOrder(TreeNode<T> node) {
        if (node == null) {
            return;
        }
        System.out.print(node.getVal() + " ");
        preOrder(node.getLeft());
        preOrder(node.getRight());
    }

    /**
     * 中序遍历
     * @param node 树节点
     * @param <T> 类型
     */
    public static <T> void inOrder(TreeNode<T> node) {
        if (node == null) {
            return;
        }
        inOrder(node.getLeft());
        System.out.print(node.getVal() + " ");
        inOrder(node.getRight());
    }

    /**
     * 后序遍历
     * @param node 树节点
     * @param <T> 类型
     */
    public static <T> void postOrder(TreeNode<T> node) {
        if (node == null) {
            return;
        }
        postOrder(node.getLeft());
        postOrder(node.getRight());
        System.out.print(node.getVal() + " ");
    }

    /**
     * 按层遍历
     * @param root 根节点
     * @param <T> 类型
     * @return 按层遍历结果
     */
    public static <T> List<T> floorLevelTree(TreeNode<T> root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<T> list = new LinkedList<>();
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode<T> node = queue.poll();
            list.add(node.getVal());
            if (node.getLeft() != null) {
                queue.offer(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.offer(node.getRight());
            }
        }
        return list;
    }

    /**
     * 求树的高度
     * @param root 根节点
     * @return 树高度
     */
    public int maxDepth(TreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }
        int floor = 1;
        int front = 0;
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.offer(root);
        int rear = queue.size();
        while (!queue.isEmpty()) {
            TreeNode<Integer> node = queue.poll();
            front++;
            if (node.getLeft() != null) {
                queue.offer(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.offer(node.getRight());
            }
            if (front == rear) {
                front = 0;
                rear = queue.size();
                if (rear != 0) {
                    floor++;
                }
            }
        }
        return floor;
    }
}
