package com.gsh.springbootquick.algorithm.sort;

import java.util.Arrays;

public class QuickSort{

  public static void main(String[] args) {
    int[] arr = new int[]{3,2,8,5,6,4,0};
    System.out.println(Arrays.toString(arr));
    int[] res = sort(arr);
    System.out.println(Arrays.toString(res));
  }

  public static int[] sort(int[] sourceArray){
      // 对 arr 进行拷贝，不改变参数内容
      int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

      return quickSort(arr, 0, arr.length - 1);
  }

  private static int[] quickSort(int[] arr, int left, int right) {
      if (left < right) {
          int partitionIndex = partition(arr, left, right);
          quickSort(arr, left, partitionIndex - 1);
          quickSort(arr, partitionIndex + 1, right);
      }
      return arr;
  }

  private static int partition(int[] arr, int left, int right) {
      // 设定基准值（pivot）
      int pivot = left;
      int index = pivot + 1;
      for (int i = index; i <= right; i++) {
          if (arr[i] < arr[pivot]) {
              swap(arr, i, index);
              index++;
          }
      }
      swap(arr, pivot, index - 1);
      return index - 1;
  }

  private static void swap(int[] arr, int i, int j) {
      int temp = arr[i];
      arr[i] = arr[j];
      arr[j] = temp;
  }

}