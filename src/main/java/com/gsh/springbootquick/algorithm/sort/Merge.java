package com.gsh.springbootquick.algorithm.sort;

import com.gsh.springbootquick.algorithm.util.SortUtils;

import java.util.Arrays;

/**
 * @author GSH
 * @create 2022/6/27 11:03
 */
public class Merge {
    public static void main(String[] args) {
        int len = 10;
        int[] arr = new int[len];
        int[] arr2 = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * 50);
        }
        System.out.println(5/2);
        System.out.println((int) Math.floor(5/2));
        System.out.println("归并排序前： " + Arrays.toString(arr));
        mergeSort(arr, arr2);
        System.out.println("归并排序后： " + Arrays.toString(arr2));
    }

    public int[] sort(int[] sourceArray) throws Exception {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        if (arr.length < 2) {
            return arr;
        }
        int middle = arr.length / 2;

        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, arr.length);

        return merge(sort(left), sort(right));
    }

    protected int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0;
        while (left.length > 0 && right.length > 0) {
            if (left[0] <= right[0]) {
                result[i++] = left[0];
                left = Arrays.copyOfRange(left, 1, left.length);
            } else {
                result[i++] = right[0];
                right = Arrays.copyOfRange(right, 1, right.length);
            }
        }
        while (left.length > 0) {
            result[i++] = left[0];
            left = Arrays.copyOfRange(left, 1, left.length);
        }
        while (right.length > 0) {
            result[i++] = right[0];
            right = Arrays.copyOfRange(right, 1, right.length);
        }
        return result;
    }

    public static void mergeSort(int[] arr, int[] arr2){
        arr2 = arr;
        int length = arr.length;
        int len = 1;
        int[] tmp;
        while(len < length) {
            int s = len;
            len = 2 * s ;
            int i = 0;
            while(i+ len <length){
                merge(arr, arr2,  i, i+ s-1, i+ len-1 ); //对等长的两个子表合并
                i = i+ len;
            }
            if(i + s < length){
                merge(arr, arr2,  i, i+ s -1, length -1); //对不等长的两个子表合并
            }
            SortUtils.swap(arr, arr2); //交换，以保证下一趟归并时，仍从arr 归并到arr2
        }
    }

    //将r[i…m]和r[m +1 …n]归并到辅助数组rf[i…n]
    public static void merge(int[] arr, int[] arr2, int i, int m, int n) {
        int j,k;
        for(j=m+1,k=i; i<=m && j <=n ; ++k){
            if(arr[j] < arr[i]) {
                arr2[k] = arr[j++];
            } else {
                arr2[k] = arr[i++];
            }
        }
        while(i <= m) {
            arr2[k++] = arr[i++];
        }
        while(j <= n) {
            arr2[k++] = arr[j++];
        }
//        System.out.println(arr2,n+1);
    }
}
