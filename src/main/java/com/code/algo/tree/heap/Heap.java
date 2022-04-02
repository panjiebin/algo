package com.code.algo.tree.heap;

/**
 * 堆
 * <p>堆的特点：
 * <ul>
 *     <li>堆是一个完全二叉树</li>
 *     <li>堆上的每一个节点的值都必须大于等于（或小于等于）其子树中每个节点的值</li>
 * </ul>
 * <p>大顶堆和小顶堆
 * <ul>
 *     <li>大顶堆：每个节点都大于等于其左右子节点</li>
 *     <li>小顶堆：每个节点都小于等于其左右子节点</li>
 * </ul>
 * @author panjb
 */
public class Heap {
    /**
     * 因为堆是一个完全二叉树，所以用数组存储，最省内存
     * <p>数组下标 i 的节点，左节点：2*i，右节点：2*i+1，父节点：i/2
     */
    private final int[] tree;
    public final int capacity;
    public int count;

    public Heap(int capacity) {
        this.capacity = capacity;
        this.tree = new int[capacity + 1];
        this.count = 0;
    }

    public static void main(String[] args) {
        Heap heap = new Heap(5);
        heap.insert(6);
        heap.insert(10);
        heap.insert(5);
        heap.insert(7);
        int max = heap.removeMax();
        while (max > -1) {
            System.out.println(max);
            max = heap.removeMax();
        }
    }

    /**
     * 插入
     * <p>
     * 堆化：先将节点插入数组末尾，然后将新插入的节点与父节点比较大小关系，当不满足大小关系时，就交换；一直迭代到满足为止
     * <p>时间复杂度：O(logn)
     * @param val 节点的值
     */
    public void insert(int val) {
        if (count > capacity) {
            return;
        }
        count++;
        tree[count] = val;
        int i = count;
        //堆化，自下而上
        //大顶堆：tree[(i >> 1)] < tree[i]
        //小顶堆：tree[(i >> 1)] > tree[i]
        while ((i >> 1) > 0 && tree[(i >> 1)] < tree[i]) {
            swap(tree, i, (i >> 1));
            i = (i >> 1);
        }
    }

    /**
     * 删除堆顶元素
     * <p>堆化，将数组最后一个值赋值给堆顶，自上而下堆化
     * <p>时间复杂度：O(logn)
     * @return 堆顶元素
     */
    public int removeMax() {
        //没有元素
        if (count == 0) {
            return -1;
        }
        int val = tree[1];
        tree[1] = tree[count];
        count--;
        heap(tree, count);
        return val;
    }

    /**
     * 自上而下堆化
     * @param tree 完全二叉树
     * @param limit 截止下标
     */
    private void heap(int[] tree, int limit) {
        int i = 1;
        while (true) {
            int maxPos = i;
            //与左子节点比较
            if (2 * i <= limit && tree[i] < tree[2 * i]) {
                maxPos = 2 * i;
            }
            //与右子节点比较
            if (2 * i + 1 <= limit && tree[maxPos] < tree[2 * i + 1]) {
                maxPos = 2 * i + 1;
            }
            if (i == maxPos) {
                break;
            }
            swap(tree, i, maxPos);
            i = maxPos;
        }
    }

    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
