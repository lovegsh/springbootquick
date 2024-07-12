package com.gsh.springbootquick.test.JUC;

/**
 * 死锁解决
 * 1. 使用`jps -l`定位进程号
 * 2. `jstack 进程号`找到死锁查看
 *
 * @author GSH
 * @create 2021/12/13 21:23
 */
public class DeadLockDemo {

    public static void main(String[] args) {

        new Thread(new HoldLockThread("lockA", "lockB"), "lockA").start();
        new Thread(new HoldLockThread("lockB", "lockA"), "lockB").start();



    }
}

class HoldLockThread implements Runnable{

    private String lockA;
    private String lockB;

    HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName());

            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName());
            }
        }
    }
}