package com.gsh.springbootquick.algorithm.algorithm;

import java.util.Arrays;

/**
 * @author GSH
 * @create 2022/6/30 10:54
 */
public class KMP {

    public static void main(String[] args) {
        String haystack = "aabaabaaf";
        String needle = "aabaaf";
        int result = strStr(haystack, needle);
        System.out.println("字符串第一次出现的位置："+result);
    }

    public static int strStr(String haystack, String needle) {
        int hLen = haystack.length();
        int nLen = needle.length();
        if (nLen == 0) {
            return 0;
        }
        int j = 0; // j 为模式串指针，表示接下来模式串比较的位置
        int[] next = getNext(needle);
        System.out.println("--------------------");
        for (int i = 0; i < hLen; i++) {
            // 如果不相等，j 回退，而 i不动
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                System.out.print("匹配到i = "+ i);
                // j 回退到[0，j)子串的最大相等前后缀的前缀尾后一个位置，如果没有相等的前后缀直接回到0 下标
                j = next[j - 1];
                System.out.println(", j回退到 "+j + "\t" + haystack);
            }
            // 如果相等 j++，循环结束 i也++，表示接下来比较的位置
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
                System.out.println("匹配正确 i = "+i+", j = "+j);
                // 模式串指针 j 已经走到最后，成功匹配
                if (j == nLen) {
                    return i - nLen + 1;
                }
            }
        }
        return -1;
    }

    public static int[] getNext(String s) {
        int len = s.length();
        int[] next = new int[len];
        int j = 0;   // 前缀指针 j=0， 后缀指针 i=1
        // j 代表接下来应该比较的前缀，同时代表这最大相等前后缀长度；i 代表接下来应该比较的后缀
        for (int i = 1; i < len; i ++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                System.out.print("back to "+ (j-1));
                // j 回退到[0，j)子串的最大相等前后缀的前缀尾后一个位置，如果没有相等的前后缀直接回到0 下标
                j = next[j - 1];
                System.out.println(", j="+j);
            }
            // 相等时 j++，表示最大相等前后缀长度+1，同时指向下一次应该比较的前缀位置
            if (s.charAt(i) == s.charAt(j)) {
                j++;
                System.out.println("the same is index "+i+", j="+j);
            }
            // next数组 i 下标填充 [0, i] 子串的最大相等前后缀长度
            next[i] = j;
            System.out.println(s+Arrays.toString(next));
        }
        return next;
    }

}
