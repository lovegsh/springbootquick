package com.gsh.springbootquick.algorithm.a;

public class HexDemo {
  public static void main(String[] args) {
    int num = 16;
    // (num >> (4×i)) & 0xf;
    System.out.println(num & 0xf);
    System.out.println(num >> (4 * 0));

    StringBuilder sb = new StringBuilder();
    // 32 位有符号整数的十六进制数有 8 位
    for (int i = 7; i >= 0; i--) {
      int val = (num >> (4 * i)) & 0xf;
      System.out.println("val = " + val);
      if (!sb.isEmpty() || val > 0) {
        char digit = val < 10 ? (char) ('0' + val) : (char) ('a' + val - 10);
        sb.append(digit);
        System.out.print("digit = " + digit);
      }
    }
    System.out.println("res = " + sb);
  }
}
