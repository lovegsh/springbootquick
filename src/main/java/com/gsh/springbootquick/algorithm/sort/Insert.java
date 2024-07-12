package com.gsh.springbootquick.algorithm.sort;

import java.util.Arrays;

public class Insert {
    public static void main(String[] args) {
        int len = 10;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
//        long before = System.currentTimeMillis();
        System.out.println("插入排序前： " + Arrays.toString(arr));
        insertSort(arr);
        System.out.println("插入排序后： " + Arrays.toString(arr));
//        System.out.println("插入排序时间： " + (System.currentTimeMillis() - before));
    }

    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int val = arr[i];   //要插入的数
            int j = i - 1;    //要插入的位置
            while (j >= 0 && val < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = val; //最后j多减了1
        }
    }

    public static void insertSort2(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];
            int insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex + 1] = insertVal;
            System.out.println("第 "+i+"次排序："+ Arrays.toString(arr));
        }
    }
}
