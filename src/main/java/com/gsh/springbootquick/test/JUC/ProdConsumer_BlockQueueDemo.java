package com.gsh.springbootquick.test.JUC;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author GSH
 * @create 2021/12/10 17:11
 *
 * 生产者消费者模式
 * 一个初始值为0的变量，两个线程对其交替操作，一个加1一个减1，进行5轮
 */
public class ProdConsumer_BlockQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue(10);

        MyResource myResource = new MyResource(blockingQueue);

        new Thread(() -> {
            try {
                myResource.product();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "生产").start();

        new Thread(() -> {
            try {
                myResource.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "消费").start();

        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println();

        myResource.stop();
    }

}

class MyResource{

    private volatile boolean FLAG = true;// 默认开启，进行生产和消费
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue){
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void product() throws InterruptedException {
        String data = null;
        boolean retValue;
        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (retValue) {
                System.out.println(Thread.currentThread().getName()+"\t 生产"+ data +"成功");
            } else {
                System.out.println(Thread.currentThread().getName()+"\t 生产"+ data +"失败");
            }
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        System.out.println(Thread.currentThread().getName()+"\t 停止生产=========");
    }

    public void consumer() throws InterruptedException {
        String data = null;
        String retValue;
        while (FLAG) {
//            data = atomicInteger.decrementAndGet() + "";
            retValue = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (retValue == null) {
                FLAG = false;
                System.out.println(Thread.currentThread().getName()+"\t 超时，停止消费*************");
                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t 消费"+ retValue +"成功");
        }
    }

    public void stop() {
        this.FLAG = false;
    }
}
