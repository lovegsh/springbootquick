package com.gsh.springbootquick.test.JUC;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author GSH
 * @create 2021/12/9 19:19
 *
 * ArrayBlockingQueue: 一个基于数组结构的有界阻塞队列，按FIFO（先进先出）排序
 * LinkedBlockingQueue： 一个基于链表结构的有界（大小默认是Integer.MAX_VALUE，可以认为无限大）阻塞队列，
 *                      按FIFO（先进先出）排序，吞吐量通常高于ArrayBlockingQueue
 * SynchronousQueue： 一个不储存元素的阻塞队列。每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量高于
 *
 * jdk13会导致先打印put2再打印take2
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {

//        arrayBlockingQueueDemo();

//        synchronousQueueDemo();

//LinkedBlockingQueue  api 类似于 ArrayBlockingQueue
    }

    public static void synchronousQueueDemo() {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+ "\t put 1");
                blockingQueue.put("1");

                System.out.println(Thread.currentThread().getName()+ "\t put 2");
                blockingQueue.put("2");

                System.out.println(Thread.currentThread().getName()+ "\t put 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "aa").start();

        new Thread(() -> {
            try {
                try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName()+ "\t take "+blockingQueue.take());
                try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName()+ "\t take "+blockingQueue.take());
                try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName()+ "\t take "+blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "bb").start();
    }

    public static void arrayBlockingQueueDemo() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        //失败报错
        System.out.println(blockingQueue.add("a"));
        blockingQueue.remove();

        //失败不报错
        System.out.println(blockingQueue.offer("a"));
        blockingQueue.poll();

        //阻塞一段时间，过时不候
        blockingQueue.offer("b", 2L, TimeUnit.SECONDS);

        //阻塞
        blockingQueue.put("a");//一直插入直到成功
        blockingQueue.take();//一直取出直到成功
    }
}
