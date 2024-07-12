package com.gsh.springbootquick.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author GSH
 * @create 2023/2/20 17:26
 */
public class BucketSort {
    public static void main(String[] args) {
        int len = 10;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * 50);
        }
        System.out.println("桶排序前： " + Arrays.toString(arr));
        bucketSort(arr);
        System.out.println("桶排序后： " + Arrays.toString(arr));
    }

    public static void bucketSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        //创建桶
        int bucketNum = (max - min) / arr.length + 1;
        List<List<Integer>> bucketArr = new ArrayList<>(bucketNum);
        for (int i = 0; i < bucketNum; i++) {
            bucketArr.add(new ArrayList<>());
        }
        //将每个元素放入桶
        for (int k : arr) {
            int num = (k - min) / (arr.length);
            bucketArr.get(num).add(k);
        }
        //对每个桶进行排序
        for (List<Integer> integers : bucketArr) {
            Collections.sort(integers);
        }
        int index = 0;
        //将桶中的元素依次取出放入原数组
        for (List<Integer> integers : bucketArr) {
            for (Integer integer : integers) {
                arr[index++] = integer;
            }
        }
    }
}
