package com.code.algo.tree;

/**
 * 二叉查找树
 * @author panjb
 */
public class BinarySearchTree {
    private TreeNode<Integer> tree;

    /**
     * 查找
     * <p> 从根节点开始查找：
     * 当根节点的值等于data时，返回根节点；
     * 当根节点的值小于data，从右子树中递归查找；
     * 当根节点的值大于data，从左子树中递归查找
     * <p>时间复杂度：O(logn)
     * @param data 数据
     * @return 数据对应的节点
     */
    public TreeNode<Integer> find(int data) {
        TreeNode<Integer> node = tree;
        while (node != null) {
            if (node.getVal() < data) {
                node = node.getRight();
            } else if (node.getVal() > data){
                node = node.getLeft();
            } else {
                return node;
            }
        }
        return null;
    }

    /**
     * 插入（没有考虑相等的情况）
     * <p>从根节点开始，
     * 当插入的数据比节点值大，且节点右子树为空，则将数据插入右子树节点的位置，否则递归右子树查找插入的位置；
     * 同理，当插入的数据节点值小，且节点左子树为空，将数据插入左子树节点的位置，否则递归左子树查找插入的位置
     * <p>时间复杂度：O(logn)
     * @param data 数据
     */
    public void insert(int data) {
        if (tree == null) {
            tree = new TreeNode<>(data);
            return;
        }
        TreeNode<Integer> node = tree;
        while (node != null) {
            if (node.getVal() < data) {
                if (node.getRight() == null) {
                    node.setRight(new TreeNode<>(data));
                    return;
                }
                node = node.getRight();
            } else {
                if (node.getLeft() == null) {
                    node.setLeft(new TreeNode<>(data));
                    return;
                }
                node = node.getLeft();
            }
        }
    }

    /**
     * 删除
     * <p>针对要删除节点的子节点个数的不同，我们需要分三种情况来处理
     * <ul>
     *     <li>如果要删除的节点没有子节点，我们只需要直接将父节点中，指向要删除节点的指针置为 null</li>
     *     <li>
     *         如果要删除的节点只有一个子节点（只有左子节点或者右子节点），
     *         我们只需要更新父节点中，指向要删除节点的指针，让它指向要删除节点的子节点就可以了
     *     </li>
     *     <li>
     *         如果要删除的节点有两个子节点，我们需要找到这个节点的右子树中的最小节点，
     *         把它替换到要删除的节点上。然后再删除掉这个最小节点，因为最小节点肯定没有左子节点（如果有左子结点，那就不是最小节点了）
     *     </li>
     * </ul>
     * <p>时间复杂度：O(logn)
     * @param data 数据
     */
    public void delete(int data) {
        //要删除节点的父节点
        TreeNode<Integer> parent = null;
        //迭代查找要删除的节点
        TreeNode<Integer> node = tree;
        while (node != null && node.getVal() != data) {
            if (node.getVal() < data) {
                parent = node;
                node = node.getRight();
            } else {
                parent = node;
                node = node.getLeft();
            }
        }
        if (node == null) {
            return;
        }
        //要删除的节点存在左右子树
        if (node.getLeft() != null && node.getRight() != null) {
            //右子树最小节点的父节点
            TreeNode<Integer> minRightParent = node;
            TreeNode<Integer> minRightNode = node.getRight();
            //迭代查找右子树的最小节点
            //终止条件为，左子树为空（如果左子树不为空，就不是最小节点了）
            while (minRightNode.getLeft() != null) {
                minRightParent = minRightNode;
                minRightNode = minRightNode.getLeft();
            }
            //将要删除节点的值设置为右子树最小节点的值
            node.setVal(minRightNode.getVal());
            //此时要删除变成右子树最小节点
            parent = minRightParent;
            node = minRightNode;
        }
        //要删除的节点是叶子节点或者只有一个子节点
        //要删除节点的子节点
        TreeNode<Integer> child;
        if (node.getLeft() != null) {
            child = node.getLeft();
        } else if (node.getRight() != null) {
            child = node.getRight();
        } else {
            child = null;
        }
        //删除的是根节点
        if (parent == null) {
            tree = child;
        } else if (node == parent.getLeft()) {
            parent.setLeft(child);
        } else {
            parent.setRight(child);
        }
    }

    public TreeNode<Integer> findMax() {
        TreeNode<Integer> node = tree;
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node;
    }

    public TreeNode<Integer> findMin() {
        TreeNode<Integer> node = tree;
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

}
