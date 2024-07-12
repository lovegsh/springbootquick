package com.gsh.springbootquick.algorithm.a;

public class YangHuiTriangle {
    public static void main(String[] args) {
        int[][] yangHui = new int[10][];

        for (int i = 0; i < yangHui.length; i++) {
            yangHui[i] = new int[i + 1];
            yangHui[i][0] = yangHui[i][i] = 1;
            for (int j = 1; j < i; j++) {
                yangHui[i][j] = yangHui[i - 1][j] + yangHui[i - 1][j - 1];
            }
        }

        for (int j = 0; j < yangHui.length; j++) {
            for (int k = 0; k < yangHui[j].length; k++) {
                System.out.print(yangHui[j][k] + "  ");
            }
            System.out.println();
        }
    }
}
