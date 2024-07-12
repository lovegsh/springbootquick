package com.gsh.springbootquick.algorithm.sort;

import com.gsh.springbootquick.algorithm.util.SortUtils;

import java.util.Arrays;

/**
 * @author GSH
 * @create 2023/2/20 16:58
 */
public class HeapSort {
    public static void main(String[] args) {
        int len = 10;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * 50);
        }
        System.out.println("堆排序前： " + Arrays.toString(arr));
        sort(arr);
        System.out.println("堆排序后： " + Arrays.toString(arr));
    }
    public static void sort(int[] arr) {
        int n = arr.length;
        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        for (int i = n - 1; i >= 0; i--) {
            SortUtils.swap(arr[0], arr[i]);// Move current root to end
            heapify(arr, i, 0);         // call max heapify on the reduced heap
        }
    }

    // To heapify a subtree rooted with node i which is an index in arr[]. n is size of heap
    public static void heapify(int[] arr, int n, int i) {
        int largest = i;  // Initialize largest as root
        int l = 2 * i + 1;  // left = 2*i + 1
        int r = 2 * i + 2;  // right = 2*i + 2
        // If left child is larger than root
        if (l < n && arr[l] > arr[largest]) {
            largest = l;
        }
        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest]) {
            largest = r;
        }
        // If largest is not root
        if (largest != i) {
            SortUtils.swap(arr[i], arr[largest]);
            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }
}