package com.gsh.springbootquick.algorithm.algorithm;

import java.util.regex.Matcher;

/**
 * @author GSH
 * @create 2022/2/18 16:23
 */
public class Pattern {

    public static void main(String[] args) throws NoSuchFieldException {

        //在这个字符串：asfsdf23323，是否符合指定的正则表达式：\w+
        //表达式对象
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("(([a-z]+)([0-9]+))");//不需要加多余的括号
        //创建Matcher对象
        Matcher m = p.matcher("aa232**ssd445");

        int i = 0;
        while(m.find()){
            System.out.println("----i="+i);
            System.out.println(m.group());//aa232  ssd445
            System.out.println(m.group(1));//aa232  ssd445
            System.out.println(m.group(2));//aa     ssd
            System.out.println(m.group(3));//232    445
            i++;
        }

    }
}
