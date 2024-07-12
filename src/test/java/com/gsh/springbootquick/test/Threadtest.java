package com.gsh.springbootquick.test;

/**
 * @author GSH
 * @create 2023/4/17 21:30
 */
public class Threadtest {
    public static void main(String args[]) {

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread() {
                public void run() {
                    pong();
                }
            };

            //        t.run();
            t.start();
            System.out.print("招商银行\n");
        }

    }

    static void pong() {
        System.out.print("信用卡");
    }
}
