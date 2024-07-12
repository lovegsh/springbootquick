package com.gsh.springbootquick.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author GSH
 * @create 2023/4/17 22:31
 */
public class ListTest {
    public static int[] dp;

    public static void main(String[] args) {
        String s = "12";
        System.out.println(s);
        dp = new int[s.length()+1];
        System.out.println(fun(s));

//        System.out.println(s.charAt(0) - '0');
    }

    public static Integer fun(String s){
        int n = s.length();
        if (n == 0) return 0;
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }
            if (i > 1 && s.charAt(i - 2) != '0' &&
                    (s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[n];
    }
}
