package com.gsh.springbootquick.algorithm.util;

/**
 * @author GSH
 * @create 2023/2/20 19:17
 */
public class SortUtils {

    public static void swap(int a, int b){
        int temp = a;
        a = b;
        b = temp;
    }

    public static void swap(int[] a, int[] b){
        int[] temp = a;
        a = b;
        b = temp;
    }
}
