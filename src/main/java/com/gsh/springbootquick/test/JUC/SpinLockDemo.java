package com.gsh.springbootquick.test.JUC;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 * <p>aa拿锁持有5秒后给bb拿锁
 * @author GSH
 * @create 2021/12/8 15:30
 */
public class SpinLockDemo {
    //原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void mylock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t lock");
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    public void myunlock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t unlock");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(() -> {
            spinLockDemo.mylock();
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("wait for 5 seconds");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myunlock();
        }, "aa").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            spinLockDemo.mylock();
            spinLockDemo.myunlock();
        }, "bb").start();
    }
}
