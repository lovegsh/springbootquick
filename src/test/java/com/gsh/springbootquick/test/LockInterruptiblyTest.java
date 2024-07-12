package com.gsh.springbootquick.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author GSH
 * @create 2022/7/28 13:49
 */
public class LockInterruptiblyTest {

    // 实例化Lock对象
    Lock lock = new ReentrantLock();

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        long timeL = 3000;
        LockInterruptiblyTest test = new LockInterruptiblyTest();

        Thread t0 = new Thread(() -> test.runThread(Thread.currentThread()));
        Thread t1 = new Thread(() -> test.runThread(Thread.currentThread()));


        t0.start();
        Thread.sleep(1000);

        t1.start();
        Thread.sleep(timeL);// 等待线程获锁时间

        t1.interrupt();// t1获取锁失败，中断t1

    }

    // 线程共同调用方法
    public void runThread(Thread t) {
        System.out.println("线程" + t.getName() + "尝试获取锁");
        // 尝试获取锁，被中断会进入中断异常处理块中
        // 这货没有时间限制的尝试获取锁，和synchronized一样，但是，这货能相应中断，这是synchronized没有的优点，这使得程序在另外的线程没有释放锁时能自定义退出程序。
        try {
            lock.lockInterruptibly();
        } catch (InterruptedException e1) {
            System.out.println("线程"+t.getName()+"被中断");
            return;
        }
//        lock.lock();
        try {
            System.out.println(t.getName() + "获取锁成功");
            for (int i = 0; i < 5; i++) {
                Thread.sleep(1000);
                System.out.println(t.getName() + "输出："+i);
            }
        } catch (InterruptedException e) {
            System.out.println("线程"+t.getName()+"被中断");
            return;
        } finally {
            lock.unlock();
            System.out.println("线程" + t.getName() + "释放了锁");
        }

    }
}

/**====================lock.lock()
 * 线程Thread-0尝试获取锁
 * Thread-0获取锁成功
 * 线程Thread-1尝试获取锁
 * Thread-0输出：0
 * Thread-0输出：1
 * Thread-0输出：2
 * Thread-0输出：3
 * Thread-0输出：4
 * Thread-1获取锁成功
 * 线程Thread-0释放了锁
 * 线程Thread-1被中断
 * 线程Thread-1释放了锁
 */

/**
 * ===================lock.lockInterruptibly();
 * 线程Thread-0尝试获取锁
 * Thread-0获取锁成功
 * 线程Thread-1尝试获取锁
 * Thread-0输出：0
 * Thread-0输出：1
 * Thread-0输出：2
 * 线程Thread-1被中断
 * Thread-0输出：3
 * Thread-0输出：4
 * 线程Thread-0释放了锁
 */
