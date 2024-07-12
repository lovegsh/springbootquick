package com.gsh.springbootquick.test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author GSH
 * @create 2022/7/27 10:05
 */
public class BlockqueueTest {
    public static void main(String[] args) throws InterruptedException {
        int limit = 2;
        int put = 5;
        int get = 5;


        MyBlockingQueueForLockSupport<Integer> lockSupport = new MyBlockingQueueForLockSupport<>(limit);
        new Thread(()->{
            for (int i = 0; i < put; i++) {
                lockSupport.put(i);
                System.out.println("lockSupport put---"+i);
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < get; i++) {
                System.out.println("lockSupport get---"+lockSupport.get());
            }
        }).start();

        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println("---------------------------");

        MyBlockingQueueForLock<Integer> lockqueue = new MyBlockingQueueForLock<>(limit);
        new Thread(()->{
            for (int i = 0; i < put; i++) {
                try {
                    lockqueue.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("BlockingQueue put---"+i);
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < get; i++) {
                try {
                    System.out.println("BlockingQueue get---"+lockqueue.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("---------------------------");

                MyBlockingQueueForLock2<Integer> lockqueue2 = new MyBlockingQueueForLock2<>(limit);
        new Thread(()->{
            for (int i = 0; i < put; i++) {
                try {
                    lockqueue2.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("BlockingQueue2 put---"+i);
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < get; i++) {
                try {
                    System.out.println("BlockingQueue2 get---"+lockqueue2.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static class MyBlockingQueueForLockSupport<T> {
        private Queue<T> queue=new LinkedList<>();
        private final int MAX;

        public MyBlockingQueueForLockSupport(int limit){
            this.MAX=limit;
        }

        public void put(T t) {
            while(queue.size()==MAX){
                LockSupport.park();
            }
            queue.offer(t);
            LockSupport.unpark(Thread.currentThread());
        }

        public T get() {
            T t;
            while (queue.size()==0){
                LockSupport.park();
            }
            t=queue.poll();
            LockSupport.unpark(Thread.currentThread());
            return t;
        }
    }

    public static class MyBlockingQueueForLock<T> {
        private Queue<T> queue=new LinkedList<>();
        private final int MAX;
        private ReentrantLock lock=new ReentrantLock();
        private Condition producer=lock.newCondition();
        private Condition consumer=lock.newCondition();

        public MyBlockingQueueForLock(int limit){
            this.MAX=limit;
        }

        public void put(T t) throws InterruptedException {
            final ReentrantLock lock=this.lock;
            lock.lockInterruptibly();
            try {
                while(queue.size()==MAX){
                    producer.await();//响应中断
                }
                queue.offer(t);
                consumer.signalAll();
            }finally {
                lock.unlock();
            }
        }

        public T get() throws InterruptedException {
            final ReentrantLock lock=this.lock;
            lock.lockInterruptibly();
            T t;
            try {
                while (queue.size()==0){
                    consumer.await();//响应中断
                }
                t=queue.poll();
                producer.signalAll();
            }finally {
                lock.unlock();
            }
            return t;
        }
    }

    public static class MyBlockingQueueForLock2<T> {
        private Queue<T> queue=new LinkedList<>();
        private final int MAX;
        private ReentrantLock lock=new ReentrantLock();
        private Condition condition=lock.newCondition();
//        private Condition consumer=lock.newCondition();

        public MyBlockingQueueForLock2(int limit){
            this.MAX=limit;
        }

        public void put(T t) throws InterruptedException {
            final ReentrantLock lock=this.lock;
            lock.lockInterruptibly();
            try {
                while(queue.size()==MAX){
                    condition.await();//响应中断
                }
                queue.offer(t);
                condition.signalAll();
            }finally {
                lock.unlock();
            }
        }

        public T get() throws InterruptedException {
            final ReentrantLock lock=this.lock;
            lock.lockInterruptibly();
            T t;
            try {
                while (queue.size()==0){
                    condition.await();//响应中断
                }
                t=queue.poll();
                condition.signalAll();
            }finally {
                lock.unlock();
            }
            return t;
        }
    }

}
