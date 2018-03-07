package com.cindychen.arithmetic.util;

/**
 * Created by cdd
 * description: 排序算法
 */

public class Sort {

    /**
     * 插入排序,从小到大
     * 把当前数插入到合适的位置
     */
    public static void inSertionSort(int[] origin) {
        int length = origin.length;
        for (int i = 1; i < length; i++) {
            int min = i;
            int temp = origin[i];
            for (int j = i - 1; j > 0; j--) {
                if (temp < origin[j]) {
                    origin[j + 1] = origin[j];
                    min = j;
                } else {
                    break;
                }
            }
            if (min != i) {
                origin[min] = temp;
            }
        }


    }

    /**
     * 冒泡排序
     * 两两比较，每次比较得到一个最大值
     */
    public static void bubbleSort(int[] origin) {
        int length = origin.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if (origin[j] > origin[j + 1]) {
                    int temp = origin[j];
                    origin[j] = origin[j + 1];
                    origin[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 选择排序
     * 从未排序的部分找出最小值，最后互换
     */
    public static void selectorInsert(int[] origin) {
        int length = origin.length;
        for (int i = 0; i < length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < length; j++) {
                if (origin[j] < origin[min]) {
                    min = j;
                }
            }
            if (min != i) {
                int temp = origin[i];
                origin[i] = origin[min];
                origin[min] = temp;
            }
        }

    }

    /**
     * 快排
     */
    public static void quickSort(int[] array, int start, int end) {
        //start < end很重要, 是递归结束的条件
        if (start < end) {
            //一次划分
            int mid = getMiddle(array, start, end);
            //递归对左右两边进行快排
            quickSort(array, start, mid - 1);
            quickSort(array, mid + 1, end);
        }
    }

    //获取数组中的中轴的位置(划分点)
    public static int getMiddle(int[] array, int start, int end) {

        //取范围内的第一个数为参考点
        int flag = array[start];

        while (start < end) {
            //大于flag的数排右边
            while (start < end && array[end] >= flag) {
                end--;
            }
            array[start] = array[end];
            //小于flag的数排左边
            while (start < end && array[start] <= flag) {
                start++;
            }
            array[end] = array[start];
        }

        //start == end的时候退出循环
        array[start] = flag;
        return start;
    }

}
