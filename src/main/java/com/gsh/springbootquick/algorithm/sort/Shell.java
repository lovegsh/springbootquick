package com.gsh.springbootquick.algorithm.sort;

import com.gsh.springbootquick.algorithm.util.SortUtils;

import java.util.Arrays;

public class Shell {
    public static void main(String[] args) {
        int len = 100000;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * len * 10 - len * 10 / 2);
        }
        long before = 0;
        if (len <= 50) {
            System.out.println("希尔排序前： " + Arrays.toString(arr));
        } else {
            before = System.currentTimeMillis();
        }

        // 冒泡快
        shellSortBubble(arr);
//        shellSortInsert(arr);

        if (len <= 50) {
            System.out.println("希尔排序后： " + Arrays.toString(arr));
        } else {
            System.out.println("希尔排序时间： " + (System.currentTimeMillis() - before));
        }

    }

    /**
     * 冒泡法
     */
    public static void shellSortBubble(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                if (arr[i] < arr[i - gap]) {
                    SortUtils.swap(arr[i], arr[i - gap]);
                }
            }
        }
    }

    /**
     * 移位法
     */
    public static void shellSortInsert(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int val = arr[i];
                int j = i - gap;
                while (j >= 0 && val < arr[j]) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = val;
            }
        }
    }

    public static void shellSort(int[] arr, int length) {
        int h = 1;
        while (h < length / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < length; i++) {
                for (int j = i; j >= h && arr[j] < arr[j - h]; j -= h) {
                    SortUtils.swap(arr[j], arr[j - h]);
                }
            }
            h = h / 3;
        }
    }
}
