package com.gsh.springbootquick.test;

import org.junit.Test;

/**
 * @author GSH
 * @create 2023/4/8 21:16
 */
public class StringTest2 {
    @Test
    public void test() {
        //0x1F600, 0x1F601
        String s = "abcdABCD";
        System.out.println(Character.codePointAt(s, 1));
        System.out.println(Character.charCount(100000));
        System.out.println(Character.isSurrogate('b'));
        System.out.println(Character.isUnicodeIdentifierPart(0x1F600));
        System.out.println("abcd".codePointAt(1));
        System.out.println("abcd".codePoints().toArray().toString());
        System.out.println("=======================");
        for (int i = 0; i < s.length(); i++) {
            System.out.println(s.codePointAt(i));
        }
    }
}
