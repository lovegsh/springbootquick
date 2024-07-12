package com.gsh.springbootquick.algorithm.sort;

import com.gsh.springbootquick.algorithm.util.SortUtils;

import java.util.Arrays;

/**
 * @author GSH
 * @create 2022/6/26 11:47
 */
public class Heap {

    public static void main(String[] args) {
        int len = 10;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * 50);
        }
        System.out.println("堆排序前： " + Arrays.toString(arr));
        heapSort(arr);
        System.out.println("堆排序后： " + Arrays.toString(arr));
    }

    public static void heapSort(int[] arr){
        int len = arr.length;
        //初始化，i从最后一个父节点开始调整
        for (int i = len / 2 - 1; i >= 0; i--) {
            maxHeapify(arr, i, len - 1);
        }
        //先将第一个元素和已经排好的元素前一位做交换，再从新调整(刚调整的元素之前的元素)，直到排序完成
        for (int i = len - 1; i > 0; i--) {
            int temp = arr[0]; arr[0] = arr[i]; arr[i] = temp;
            maxHeapify(arr, 0, i - 1);
        }
    }

    public static void maxHeapify(int[] arr, int start, int end) {
        //建立父节点指标和子节点指标
        int dad = start;
        int son = dad * 2 + 1;
        while (son <= end) { //若子节点在范围内才做比较
            if (son + 1 <= end && arr[son] < arr[son + 1]) { //先比较两个子节点指标，选择最大的
                son++;
            }
            if (arr[dad] > arr[son]) { //如果父节点大于子节点代表调整完成，直接跳出函数
                return;
            } else { //否则交换父子內容再继续子节点与孙节点比較
                SortUtils.swap(arr[son], arr[dad]);
                dad = son;
                son = dad * 2 + 1;
            }
//            System.out.println("---------"+Arrays.toString(arr));
        }
    }

}
