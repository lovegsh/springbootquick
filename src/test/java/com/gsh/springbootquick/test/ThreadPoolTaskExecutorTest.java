package com.gsh.springbootquick.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.*;

/**
 * @author GSH
 * @create 2023/3/13 14:24
 * 可以看到非核心数线程执行完毕之后，队列中的task进入继续执行，等再次进入队列的task结束后，
 * 可以看到总线程数减少了1，而等核心线程执行完毕后，发现总线程数没有减少，但活跃线程数减少，
 * 也就是核心线程数没有回收。书上说的是正确的，大部分网上的博客说的keepAliveTime=0永久不回收是有出入的。
 *
 * 如果要设置核心线程的回收，则需要设置 executor.allowCoreThreadTimeOut(true);
 */
@Slf4j
public class ThreadPoolTaskExecutorTest implements Runnable {

    private int i = 0;

    public ThreadPoolTaskExecutorTest(int i) {
        this.i = i;
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 0, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(1),Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 6; i++) {
            executor.execute(new ThreadPoolTaskExecutorTest(i));
        }
        while (true) {
            System.out.println("总线程数：" + executor.getPoolSize() + "当前活跃线程数：" + executor.getActiveCount());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void run() {
        System.out.println("i=" + i + " Thread = " + Thread.currentThread().getName());
        if (i >= 1) {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("i=" + i + " sleep 1 s结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("i=" + i + " sleep 3 s结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void test() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(1), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        MyTask t1 = new MyTask("id:1");
        MyTask t2 = new MyTask("id:2");
        MyTask t3 = new MyTask("id:3");
        MyTask t4 = new MyTask("id:4");
        MyTask t5 = new MyTask("id:5");
        MyTask t6 = new MyTask("id:6");
        MyTask t7 = new MyTask("id:7");

        executor.execute(t1);
        executor.execute(t2);
        executor.execute(t3);
        log.error("===1 总线程数：" + executor.getPoolSize() + "当前活跃线程数：" + executor.getActiveCount());
        executor.execute(t4);
        log.error("===2 总线程数：" + executor.getPoolSize() + "当前活跃线程数：" + executor.getActiveCount());
        executor.execute(t5);
        log.error("===3 总线程数：" + executor.getPoolSize() + "当前活跃线程数：" + executor.getActiveCount());
        executor.execute(t6);
        log.error("===4 总线程数：" + executor.getPoolSize() + "当前活跃线程数：" + executor.getActiveCount());
        executor.execute(t7);
        log.error("===5 总线程数：" + executor.getPoolSize() + "当前活跃线程数：" + executor.getActiveCount());
    }
}

class MyTask implements Runnable {
    private String id;

    public MyTask(String id) {
        this.id = id;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + "-" + id);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}