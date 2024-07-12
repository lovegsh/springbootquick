package com.gsh.springbootquick.test.JUC;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author GSH
 * @create 2021/12/9 19:04
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        // 模拟3个停车位
        Semaphore semaphore = new Semaphore(3);

        //模拟6辆汽车
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t抢到车位");
                    try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
                    System.out.println(Thread.currentThread().getName()+"\t3秒后离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
