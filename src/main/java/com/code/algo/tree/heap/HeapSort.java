package com.code.algo.tree.heap;

import java.util.*;

/**
 * 堆排序
 * <p>时间复杂度：O(nlogn)</p>
 * @author panjb
 */
public class HeapSort {

    public static void main(String[] args) {
        //数组存储完全二叉树，下标0不存储数据
        int[] arr = {-1, 5, 7, 3, 9, 8, 10};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 堆排序
     * <p>堆排序主要分为两步：建堆和排序。
     * 建堆完成后，数组中的元素就是按照大顶堆的特性来组织的。数组中的第一个元素就是堆顶元素，也就是数组的最大值。
     * 我们将第一个元素与n进行交换，就把最大值放到下标n。然后对剩下的 n-1 个元素进行堆化，以此类推，完成堆排序。
     * <p>建堆时间复杂度：O(n)，排序时间复杂度：O(nlogn)，整体时间复杂度：O(nlogn)
     * <p>原地排序，不稳定排序
     * @param arr 数组，排序[1, arr.length - 1]的数据
     */
    public static void sort(int[] arr) {
        buildHeap(arr, arr.length - 1);
        int k = arr.length - 1;
        while (k > 1) {
            swap(arr, k, 1);
            k--;
            heap(arr, k, 1);
        }
    }

    /**
     * 建堆
     * <p>
     * 从后往前处理数组数据，每个数据都从上往下堆化。
     * 由于叶子节点往下堆化只能跟自己比较，所以我们直接从 <b>最后一个非叶子节点</b> 开始
     * <p>
     * 对于一直长度为 n 的数组构成的完全二叉树，非叶子节点范围：[1,(n-1)/2]，叶子节点：[(n-1)/2 + 1,n-1].
     * 因此我们只有对[1,(n-1)/2]的节点进行堆化即可。
     * <p>时间复杂度为O(n)
     * @param arr 数组
     * @param n 数组元素个数，n = arr.length - 1
     */
    private static void buildHeap(int[] arr, int n) {
        for (int i = n / 2; i >= 1; i--) {
            heap(arr, n, i);
        }
    }

    private static void heap(int[] arr, int limit, int i) {
        while (true) {
            int maxPos = i;
            if (2 * i <= limit && arr[2 * i] > arr[i]) {
                maxPos = 2 * i;
            }
            if ((2 * i + 1) <= limit && arr[(2 * i + 1)] > arr[maxPos]) {
                maxPos = (2 * i + 1);
            }
            if (maxPos == i) {
                break;
            }
            swap(arr, i, maxPos);
            i = maxPos;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
