package com.gsh.springbootquick.algorithm.sort;

import com.gsh.springbootquick.algorithm.util.SortUtils;

import java.util.Arrays;

public class Select {

    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        System.out.println("选择排序前： " + Arrays.toString(arr));
        selectSort(arr);
        System.out.println("选择排序后： " + Arrays.toString(arr));
    }

    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                SortUtils.swap(arr[minIndex], arr[i]);
            }
        }
    }

    public static void selectSort2(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                SortUtils.swap(arr[minIndex], arr[i]);
            }
            System.out.println("第 "+(i+1)+"次排序： " + Arrays.toString(arr));
        }
    }
}
