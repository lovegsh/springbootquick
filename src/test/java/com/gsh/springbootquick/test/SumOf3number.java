package com.gsh.springbootquick.test;

import java.util.Arrays;

/**
 * @author GSH
 * @create 2022/8/27 13:59
 */
public class SumOf3number{
    public static void main(String[] args) {
        int[] nums = {-5,-5,-4,0,0,3,3,4,5};
        int target = -2;
        int out = threeSumClosest(nums, target);
        System.out.println("最接近-2的答案是: "+ out);
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int res = Integer.MAX_VALUE;
        for(int a = 0; a < n - 2; a++){
            if(a > 0 && nums[a] == nums[a-1]) continue;
            int b = a + 1, c = n - 1;
            while(b < c){
                int sum = nums[a] + nums[b] + nums[c];
                if(sum == target) return target;
//                System.out.println("sum = "+ sum +", res = "+res);
//                System.out.println("sum - target = "+Math.abs(sum - target)+ ",  res - target = "+Math.abs(res-target));
                if(Math.abs(sum - target) < Math.abs(res - target)){
                    res = sum;
                }
//                System.out.println("a,b,c = "+nums[a]+"  "+nums[b]+"  "+nums[c]);
//                System.out.println("==========================");
                if(sum > target){
                    c--;
                } else if(sum < target) {
                    b++;
                } else {
                    return target;
                }
            }
        }
        return res;
    }
    //-5,-5,-4,0,0,3,3,4,5
}
