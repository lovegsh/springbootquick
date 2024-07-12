package com.gsh.springbootquick.algorithm.algorithm;

import java.util.Arrays;

/**
 * @author GSH
 * @create 2022/10/20 22:27
 */
public class DP {

    public static void main(String[] args) {
        int[] cost = new int[]{10,15,20};
//        int[] cost = new int[]{1,100,1,1,1,100,1,1,100,1};
        System.out.println(Arrays.toString(cost));
        minCostClimbingStairs(cost);
    }


    public static int minCostClimbingStairs(int[] cost) {
        //动态规划方程 dp[i]=min{dp[n-1],dp[n-2]}+cost[i]
        //由于最后一步不支付,看倒数一步和倒数二步哪个更小
        //最后dp1=dpi,指向倒数一步
        int dp0 = cost[0];
        int dp1 = cost[1];
        int dpi;
        for(int i = 2; i < cost.length; i++){
            dpi = Math.min(dp0, dp1) + cost[i];
            dp0 = dp1;
            dp1 = dpi;
            System.out.println("dp0="+dp0+", dp1="+dp1+", dp"+i+"="+dpi);
        }
        return Math.min(dp0, dp1);
    }
}
