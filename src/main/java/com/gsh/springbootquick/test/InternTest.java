package com.gsh.springbootquick.test;

/**
 * String intern() test
 */
public class InternTest {

    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();

        String str2 = new StringBuilder("Ja").append("va3").toString();

        String str3 = "java3";
        String str4 = new String("java3");
        String str5 = new StringBuilder("Jav").append("a3").toString();
        String str6 = new StringBuilder("Ja").append("v").append("a3").toString();

        System.out.println(str1.intern() == str1);//因为之前没有所以创建的引用和intern()返回的引用相同

        System.out.println(str2.intern() == str2);//
        System.out.println("==============1");
        System.out.println(str3 == str4);
        System.out.println(str3 == str4.intern());//常量池中已经有str3的引用，str4.intern()发现常量池中有了直接取str3
        System.out.println(str2 == str3);
        System.out.println(str2 == str4);
        System.out.println(str2 == str4.intern());
        System.out.println("==============2");
        System.out.println(str2 == str5);
        System.out.println(str3 == str5);
        System.out.println(str2 == str5.intern());
        System.out.println(str3 == str5.intern());
        System.out.println(str2 == str6.intern());

        System.out.println("==============3");
        String s1 = new StringBuilder("pk").append("slow").toString();
        System.out.println(s1.intern() == s1);

        String s2 = new StringBuilder("pk").append("slow").toString();
        System.out.println(s2.intern() == s2);
    }
}
