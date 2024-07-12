package com.gsh.springbootquick.test;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author GSH
 * @create 2022/12/3 15:05
 */
public class StringTest {

    public static void main(String[] args) {
        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);
//        System.out.println(s.equals(s2));

        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);
//        System.out.println(s3.equals(s4));

//        JDK 6 输出 : false false
//        JDK 7 输出 : false true

        System.out.println("===================");

        String str1 = new StringBuilder("this is ").append("a string").toString();
        //执行完这行代码后，常量池中会有"this is "和"a string"，但是不会有"this is a string"。
//        System.out.println(str1.intern() == str1);//true
        String str2 = new String("this is a string");
        //执行完这行代码后，常量池中会有"this is a string"。
        System.out.println(str2 == str2.intern());//false
        System.out.println(str1 == str2);//f
        System.out.println("===================");
        String str3 = "this is a string";
        System.out.println(str1 == str3);//
        System.out.println(str2 == str3);//

    }

    List<String> list = new ArrayList<>();

    @Test
    void contextLoads() {
        testPoints(7);
        testPoints(7,9,11);
        testPoints(new Integer[]{7,9,11});

        test2("aa", "bb", "cc");
    }

    public static void testPoints(Integer... itgr) {
        if (itgr.length == 0) {
            System.out.println("没有Integer参数传入！");
        } else if (itgr.length == 1) {
            System.out.println("1个Integer参数传入！");
        } else {
            System.out.println("the input string is-->");
            for (int i = 0; i < itgr.length; i++) {
                System.out.println("第" + (i + 1) + "个Integer参数是" + itgr[i] + ";");
            }
        }
    }

    @Test
    void contextLoads2() {
        test2("aa", "bb", "cc");
        System.out.println();
        test2(new String[]{"aa", "bb", "cc"});
    }

    void test2(String... s){
        StringBuilder str = new StringBuilder();
        for (String s1 : s) {
            str.append(s1);
        }
        System.out.println(str);
        System.out.println(Arrays.toString(s));
        System.out.println(s.getClass());
    }

    @Test
    void contextLoads3() {
        //		test3("aa", "bb", "cc");
        //		System.out.println(list);
        //		System.out.println(new String[]{"aa", "bb", "cc"});
        System.out.println(new char[]{'a', '1', 1});
        //		System.out.println(new char[]{'a', '1', 1}.getClass());
    }

    void test3(String... s){
        Assert.notNull(s, "must not be null");
        Collections.addAll(list, s);
    }

    @Test
    public void test4(){
        String s = "324";
        Integer integer = Integer.valueOf(s);
        System.out.println(Integer.valueOf(s));
        int i = Integer.parseInt(s);
        System.out.println(Integer.parseInt(s));
    }
}
