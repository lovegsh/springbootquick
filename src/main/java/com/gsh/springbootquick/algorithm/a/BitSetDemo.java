package com.gsh.springbootquick.algorithm.a;

import java.util.BitSet;

/**
 * @author GSH
 * @create 2022/6/26 11:24
 */
public class BitSetDemo {

    public static void main(String[] args) {
        BitSet b = new BitSet();
        BitSet b2 = new BitSet();
        b.set(1);
        b2.set(2);
        b.or(b2);

        System.out.println(b);
        System.out.println(b2);



    }

}
