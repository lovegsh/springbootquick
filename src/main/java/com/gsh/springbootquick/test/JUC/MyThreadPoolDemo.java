package com.gsh.springbootquick.test.JUC;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author GSH
 * @create 2021/12/11 21:45
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {

        System.out.println(Runtime.getRuntime().availableProcessors());

        //自定义线程池maximum
//        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
//                2,
//                5,
//                1L, TimeUnit.SECONDS,
//                new LinkedBlockingDeque<Runnable>(3),
//                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.CallerRunsPolicy());
//
//        for (int i = 0; i < 25; i++) {
//            int finalI = i;
//            threadPool.execute(() -> {
//                System.out.println(Thread.currentThread().getName()+"\t"+ finalI);
//            });
//        }
//        threadPool.shutdown();

        System.out.println();

        //使用工具类创建
//        toolThreadPool();


        PausableThreadPoolExecutor pausablePool = new PausableThreadPoolExecutor();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            pausablePool.execute(() -> {
                System.out.println(Thread.currentThread().getName()+"\t"+ finalI);
            });
            if (i == 5) {
                pausablePool.pause();
                break;
            }
        }
        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
        pausablePool.resume();

        pausablePool.shutdown();

    }

    public static void toolThreadPool() {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            pool.execute(() -> {
                System.out.println(Thread.currentThread().getName());
            });
        }
        pool.shutdown();

        System.out.println();

        ExecutorService pool2 = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            pool2.execute(() -> {
                System.out.println(Thread.currentThread().getName());
            });
        }
        pool2.shutdown();

        //睡眠一秒因为第二种单线程操作需要时间，还没走完就开始后面
        //        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println();

        ExecutorService pool3 = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            pool3.execute(() -> {
                System.out.println(Thread.currentThread().getName());
            });
        }
        pool3.shutdown();

        System.out.println();

        ExecutorService pool4 = Executors.newScheduledThreadPool(3);
        for (int i = 0; i < 10; i++) {
            pool4.execute(() -> {
                System.out.println(Thread.currentThread().getName());
            });
        }
        pool4.shutdown();

        System.out.println();

        ExecutorService pool5 = Executors.newWorkStealingPool();
        for (int i = 0; i < 10; i++) {
            pool5.execute(() -> {
                System.out.println(Thread.currentThread().getName());
            });
        }
        pool5.shutdown();
    }

    //可暂停线程池
    static class PausableThreadPoolExecutor extends ThreadPoolExecutor {
        private boolean isPaused;
        private ReentrantLock pauseLock = new ReentrantLock();
        private Condition unpaused = pauseLock.newCondition();

        public PausableThreadPoolExecutor() {
            super(2, 5, 1L, TimeUnit.SECONDS,
                    new LinkedBlockingDeque<Runnable>(5));
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            super.beforeExecute(t, r);
            pauseLock.lock();
            try {
                while (isPaused) {
                    unpaused.await();
                }
            } catch (InterruptedException ie) {
                t.interrupt();
            } finally {
                pauseLock.unlock();
            }
        }

        public void pause() {
            pauseLock.lock();
            try {
                isPaused = true;
            } finally {
                pauseLock.unlock();
            }
        }

        public void resume() {
            pauseLock.lock();
            try {
                isPaused = false;
                unpaused.signalAll();
            } finally {
                pauseLock.unlock();
            }
        }
    }
}

