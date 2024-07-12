package com.gsh.springbootquick.algorithm.sort;

import com.gsh.springbootquick.algorithm.util.SortUtils;

import java.util.Arrays;

public class Bubble {

    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        System.out.println("冒泡排序前："+Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println("冒泡排序后："+Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    SortUtils.swap(arr[j],arr[j + 1]);
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }

    public static void bubbleSort2(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println("第 "+(i+1)+"次排序："+ Arrays.toString(arr));
        }
    }
}
