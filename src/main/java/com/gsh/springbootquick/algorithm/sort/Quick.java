package com.gsh.springbootquick.algorithm.sort;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Quick {

    static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        int len = 10;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * 50);
        }
//        long before = System.currentTimeMillis();
        System.out.println("快速排序前： " + Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println("快速排序后： " + Arrays.toString(arr));
//        System.out.println("快速排序时间： " + (System.currentTimeMillis() - before));
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int l = left;
            int r = right;
            int pivot = arr[left];
            while (l < r) {
                while (l < r && arr[r] >= pivot) {
                    r--;
                }
                arr[l] = arr[r];
                while (l < r && arr[l] <= pivot) {
                    l++;
                }
                arr[r] = arr[l];
            }
            arr[l] = pivot;

            quickSort(arr, left, l);
            quickSort(arr, l + 1, right);
        }
    }

    public static void quickSort2(int[] arr, int left, int right) {
        if (left < right) {
            int l = left;
            int r = right;
            int pivot = arr[left];
            while (l < r) {
                while (l < r && arr[r] >= pivot) {
                    r--;
                }
                if (l < r) {
                    arr[l] = arr[r];
                }
                while (l < r && arr[l] <= pivot) {
                    l++;
                }
                if (l < r) {
                    arr[r] = arr[l];
                }
                System.out.println("第 "+(atomicInteger.incrementAndGet())+"交换后："+Arrays.toString(arr));
            }
            arr[l] = pivot;
            System.out.println("第"+(atomicInteger.incrementAndGet())+"次pivot："+Arrays.toString(arr));

            quickSort2(arr, left, l);
            quickSort2(arr, l + 1, right);
        }
    }
}
