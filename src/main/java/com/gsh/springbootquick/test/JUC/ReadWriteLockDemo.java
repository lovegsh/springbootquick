package com.gsh.springbootquick.test.JUC;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 *
 * 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行
 * 如果有一个线程想去写共享资源，就不应该有其它线程可以对该资源进行读或写
 *      读-读 能共存
 *      读-写 不能共存
 *      写-写 不能共存
 *
 *      写操作：原子+独占
 *
 *      jdk13需要在中间sleep才能达到jdk8的效果
 * @author GSH
 * @create 2021/12/9 14:19
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i < 5; i++) {
            final int finalI = i;
            new Thread(() -> {
                myCache.put(finalI +"", finalI +"");
            }, String.valueOf(i)).start();
        }

//        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }

        for (int i = 0; i < 5; i++) {
            final int finalI = i;
            new Thread(() -> {
                myCache.get(finalI +"");
            }, String.valueOf(i)).start();
        }
    }
}

// 资源类
class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
//    private Lock lock = new ReentrantLock();
    private ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        rwlock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在写入：" + key);
            try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName()+"\t 写入完成：");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rwlock.writeLock().unlock();
        }
    }

    public void get(String key) {
        rwlock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在读取：");
            try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读取完成："+result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rwlock.readLock().unlock();
        }
    }

    public void clear() {
        map.clear();
    }

//    public void put(String key, Object value) {
//        lock.lock();
//        try {
//            System.out.println(Thread.currentThread().getName()+"\t 正在写入：" + key);
//            try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
//            map.put(key, value);
//            System.out.println(Thread.currentThread().getName()+"\t 写入完成：");
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            lock.unlock();
//        }
//    }
//
//    public void get(String key) {
//        System.out.println(Thread.currentThread().getName()+"\t 正在读取：");
//        try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
//        Object result = map.get(key);
//        System.out.println(Thread.currentThread().getName()+"\t 读取完成："+result);
//    }
}

