package com.gsh.springbootquick.algorithm.sort;

/**
 * @author GSH
 * @create 2022/6/25 16:45
 */
public class TimeTestAll {

    public static void main(String[] args) {
        int[] array = new int[20000];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 1000000);
        }
//        System.out.println("排序之前："+ Arrays.toString(arr));

        int[] arr = array;
        long now = System.currentTimeMillis();
        Bubble.bubbleSort(arr);
        System.out.println("冒泡排序："+(System.currentTimeMillis()-now));

        arr = array;
        now = System.currentTimeMillis();
        Select.selectSort(arr);
        System.out.println("选择排序："+(System.currentTimeMillis()-now));

        arr = array;
        now = System.currentTimeMillis();
        Insert.insertSort(arr);
        System.out.println("插入排序："+(System.currentTimeMillis()-now));

        arr = array;
        now = System.currentTimeMillis();
        Quick.quickSort(arr, 0, array.length - 1);
        System.out.println("快速排序："+(System.currentTimeMillis()-now));

        arr = array;
        now = System.currentTimeMillis();
        Heap.heapSort(arr);
        System.out.println("堆排序："+(System.currentTimeMillis()-now));

        arr = array;
        now = System.currentTimeMillis();
        HeapSort.sort(arr);
        System.out.println("2堆排序："+(System.currentTimeMillis()-now));

        arr = array;
        now = System.currentTimeMillis();
        MergeSort.mergeSort(arr);
        System.out.println("归并排序："+(System.currentTimeMillis()-now));

        arr = array;
        now = System.currentTimeMillis();
        BucketSort.bucketSort(arr);
        System.out.println("桶排序："+(System.currentTimeMillis()-now));

        arr = array;
        now = System.currentTimeMillis();
        Shell.shellSortBubble(arr);
        System.out.println("希尔排序："+(System.currentTimeMillis()-now));



    }
}
