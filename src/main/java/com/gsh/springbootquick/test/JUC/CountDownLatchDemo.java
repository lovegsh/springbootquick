package com.gsh.springbootquick.test.JUC;

import java.util.concurrent.CountDownLatch;

/**
 * 用于启动一个服务时，主线程需要等待多个组件加载完毕之后再继续执行
 * @author GSH
 * @create 2021/12/9 16:20
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        closedoor();
    }

    public static void closedoor() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        //        for (int i = 0; i < 6; i++) {
        //            new Thread(()->{
        //                System.out.println(Thread.currentThread().getName()+ "\t离开");
        //            },String.valueOf(i)).start();
        //        }

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+ "\t离开");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();

        System.out.println(Thread.currentThread().getName()+ "\t main离开");
    }
}
