package com.code.algo.search;

/**
 * 查找算法
 *
 * @author panjb
 */
public class Search {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 5, 6, 8, 8, 10};
        int[] arr2 = new int[]{4, 5, 6, 1, 2, 3};
//        int idx = bSearch2(arr, 6, 0, arr.length - 1);
        int idx = bSearchN(arr2, 2);
        System.out.println("idx = " + idx);
    }

    /**
     * 二分查找法（非递归版本），递归版本见{@link #bSearch2(int[], int, int, int)}
     * <p>每次将要查找的值与区间的中间元素比较，将待查找区间缩小为之前的一半，直到找到要查找的元素，或者区间为0
     * <p>时间复杂度：O(logn)
     * @param arr 数组
     * @param value 要查找的值
     * @return 存在，返回对应值的下标，否则返回-1
     */
    public static int bSearch(int[] arr, int value) {
        if (arr == null) {
            return -1;
        }
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] == value) {
                return mid;
            } else if (arr[mid] < value) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    /**
     * 二分查找变形-查找第一个值等于给定值的元素
     * @param arr 数组
     * @param value 给定值
     * @return 查找的元素
     */
    public static int bSearchFirstEq(int[] arr, int value) {
        if (arr == null) {
            return -1;
        }
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] < value) {
                low = mid + 1;
            } else if (arr[mid] > value) {
                high = mid - 1;
            } else {
                //当中间元素是第一个元素时或者mid-1不等于给定值，mid就是要查找的元素
                if (mid == 0 || arr[mid - 1] != value) {
                    return mid;
                }
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 二分查找变形-查找最后一个值等于给定值的元素
     * @param arr 数组
     * @param value 给定值
     * @return 查找的元素
     */
    public static int bSearchLastEq(int[] arr, int value) {
        if (arr == null) {
            return -1;
        }
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] < value) {
                low = mid + 1;
            } else if (arr[mid] > value) {
                high = mid - 1;
            } else {
                //当中间元素是最后一个元素时或者mid+1不等于给定值，mid就是要查找的元素
                if (mid == arr.length - 1 || arr[mid + 1] != value) {
                    return mid;
                }
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 二分查找变形-查找第一个值大于等于给定值的元素
     * @param arr 数组
     * @param value 给定值
     * @return 查找的元素
     */
    public static int bSearchFirstGe(int[] arr, int value) {
        if (arr == null) {
            return -1;
        }
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] < value) {
                low = mid + 1;
            } else {
                //第一个元素，或者mid左边元素小于给定值
                if (mid == 0 || arr[mid - 1] < value) {
                    return mid;
                }
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 二分查找变形-查找最后一个值小于等于给定值的元素
     * @param arr 数组
     * @param value 给定值
     * @return 查找的元素
     */
    public static int bSearchLastLq(int[] arr, int value) {
        if (arr == null) {
            return -1;
        }
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] > value) {
                high = mid - 1;
            } else {
                //最后一个元素，或者mid右边大于给定值
                if (mid == arr.length - 1 || arr[mid + 1] > value) {
                    return mid;
                }
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 二分查找循环有序数组
     * <p>核心思想是每一次二分，都会将待排序区间划分为一个有序区间和一个无序区间，根据给定值是否在有序区间调整查找范围
     * @param arr 数组
     * @param value 给定值
     * @return 查找元素下标
     */
    public static int bSearchN(int[] arr, int value) {
        if (arr == null) {
            return -1;
        }
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] == value) {
                return mid;
            }
            //前面有序
            if (arr[0] <= arr[mid]) {
                //给定值在有序区间内
                if (value >= arr[low] && value <= arr[high]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                //给定值在有序区间内
                if (value >= arr[mid + 1] && value <= arr[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    public static int bSearch2(int[] arr, int value, int start, int end) {
        if (arr == null) {
            return -1;
        }
        int mid = start + ((end - start) >> 1);
        if (arr[mid] == value) {
            return mid;
        } else if (arr[mid] < value) {
            return bSearch2(arr, value, mid + 1, end);
        } else {
            return bSearch2(arr, value, start, mid - 1);
        }
    }
}
