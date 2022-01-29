package com.code.algo.sort;

import java.util.Arrays;

/**
 * 排序
 *
 * @author panjb
 */
public class Sort {

    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 2, 3, 1, 6};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 冒泡排序
     * <p>冒泡排序只会操作相邻的两个数据。
     * 每次冒泡操作都会对相邻的元素进行比较，当不满足大小关系时，交换两个元素。一趟冒泡会让至少一个元素移动到它应该到的位置
     * <ul>
     *     <li>原地排序</li>
     *     <li>稳定排序</li>
     *     <li>最好时间复杂度：O(n)，数据有序时</li>
     *     <li>最差时间复杂度：O(n^2)，数据逆序时</li>
     *     <li>平均时间复杂度：O(n^2)</li>
     * </ul>
     * @param arr 排序数组
     */
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        //n次冒泡
        for (int i = 0; i < arr.length; i++) {
            //标记是否有数据交换，当无数据交换时，排序完成，可以提前跳出循环
            boolean flag = false;
            //一次冒泡，将至少一个元素移动到它应该到的位置
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j+1]) {
                    swap(arr, j, j + 1);
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    /**
     * 插入排序
     * <p>将数组中的元素分成已排序区间和未排序区间，初始已排序区间只有一个元素，即数组的第一个元素。
     * 插入排序的核心思想：取未排序区间元素插入到已排序区间的合适位置，保证已排序区间仍然有序。
     * 重复这个过程，直到未排序区间元素为空。
     * <p>插入排序涉及元素的比较和元素的移动，要将一个元素a插入到已排序区间，需要将元素a与已排序区间依次比较，
     * 找到合适的插入位置。在找到插入点之后，需要将插入点之后的数据往后移动一位，即腾出位置插入a
     * <ul>
     *     <li>原地排序</li>
     *     <li>稳定排序</li>
     *     <li>最好时间复杂度：O(n)，排序数据刚好有序</li>
     *     <li>最坏时间复杂度：O(n^2)，排序数据刚好有序</li>
     *     <li>平均时间复杂度：O(n^2)，数组中插入一个数据平均时间复杂度为O(n)，插入n个数据</li>
     * </ul>
     * @param arr 数组
     */
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            int val = arr[i];
            //查找合适的插入位置
            for (; j >= 0; j--) {
                if (arr[j] > val) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = val;
        }
    }

    /**
     * 选择排序
     * <p>选择排序也将数组分成已排序区间和未排序区间，每次从未排序区间取最小的元素，插入到已排序区间末尾
     * <ul>
     *     <li>原地排序</li>
     *     <li>不稳定排序</li>
     *     <li>最好时间复杂度：O(n^2)</li>
     *     <li>最坏时间复杂度：O(n^2)</li>
     *     <li>平均时间复杂度：O(n^2)</li>
     * </ul>
     * @param arr 数组
     */
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            //未排序区间最小元素下标
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            //减少不必要的交换
            if (i != min) {
                swap(arr, i, min);
            }
        }
    }

    /**
     * 交换数组两个元素
     * @param i 下标
     * @param j 下标
     * @param arr 数组
     */
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 归并排序
     * <p>排序一个数组，将数组按中间分成前后两个部分，然后对前后两个部分排序，再将两个部分合并在一起，这样整个数组就有序了
     * <p>这是一种分治思想，通过递归实现
     * <p>递推公式：merge_sort(p...r) = merge(merge_sort(p...q), merge_sort(q+1...r))，其中q=(p+r)/2
     * <p>终止条件：p >= r
     * <ul>
     *     <li>非原地排序：空间复杂度：O(n)</li>
     *     <li>稳定排序</li>
     *     <li>时间复杂度：O(nlogn)</li>
     * </ul>
     * @param arr 数组
     */
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        doMergeSort(arr, 0, arr.length - 1);
    }

    private static void doMergeSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int middle = (start + end) / 2;
        doMergeSort(arr, start, middle);
        doMergeSort(arr, middle + 1, end);
        merge(arr, start, middle, end);
    }

    /**
     * 合并一个数组中两个有序部分
     * @param arr 原数组
     * @param start 起始下标
     * @param middle 中间下标
     * @param end 结束下标
     */
    private static void merge(int[] arr, int start, int middle, int end) {
        //临时保存左半部分数据，start~middle
        int[] tmpLeft = new int[middle - start + 2];
        //临时保存右半部分数据，middle+1~end
        int[] tmpRight = new int[end - middle + 1];
        //通过哨兵，简化编程
        tmpLeft[tmpLeft.length - 1] = Integer.MAX_VALUE;
        tmpRight[tmpRight.length - 1] = Integer.MAX_VALUE;
        for (int i = start, j= 0; i <= middle; i++) {
            tmpLeft[j] = arr[i];
            j++;
        }
        for (int i = middle + 1, j = 0; i <= end; i++) {
            tmpRight[j] = arr[i];
            j++;
        }
        for (int i = 0, j = 0, k = start; i <= end; i++) {
            if (tmpLeft[j] <= tmpRight[k]) {
                arr[start + i] = tmpLeft[j];
                j++;
            } else {
                arr[start + i] = tmpRight[k];
                k++;
            }
        }
    }

    /**
     * 快速排序
     * <p>排序数组中下标p到r的一组数据，先选择p到r之间的任意元素作为分区点(pivot)，
     * 然后遍历p到r之间的元素，将小于pivot的元素放到左边，将大于pivot的元素放到右边，将pivot放到中间（q）。
     * 这样p到r之间的数据就被分成三个部分，p到q-1小于pivot，q等于pivot，q+1到r大于pivot。
     * 通过分治和递归思想，可以继续递归排序p~q-1和q+1~r的数据，知道排序区间为1。
     * <p>递推公式：quick_sort(p...r) = quick_sort(p...q-1) + quick_sort(q+1...r)，其中q分区点所在下标
     * <p>终止条件：p >= r
     * @param arr 数组
     */
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        doQuickSort(arr, 0, arr.length - 1);
    }

    private static void doQuickSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        //获取分区点
        int q = partition(arr, start, end);
        doQuickSort(arr, start, q - 1);
        doQuickSort(arr, q + 1, end);
    }

    /**
     * 分区
     * <p>取下标end为分区点pivot，通过游标i将[start, end]，分成已处理区间[start,i-1]，未处理区间[i, end - 1]，
     * 从未处理区间取元素与pivot比较，小于pivot的元素插入已处理区间尾部，直到未处理区间为空。
     *
     * @param arr 原始数组
     * @param start 起始下标
     * @param end 结束下标
     * @return 分区下标
     */
    private static int partition(int[] arr, int start, int end) {
        int pivot = arr[end];
        //将分区区间分成已处理区间和未处理区间
        int i = start;
        for (int j = start; j <= end - 1; j++) {
            if (arr[j] <= pivot) {
                //由于不需要保证数组有序，通过交换，可以减少插入时的数据移动
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, end);
        return i;
    }
}
