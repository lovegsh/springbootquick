package com.gsh.springbootquick.test;

/**
 * @author GSH
 * @create 2022/7/24 14:57
 */
public class ConcurrentHashmapTest {

//    public static void main(String[] args) {
//        ConcurrentHashMap cmap = new ConcurrentHashMap<>();
//    }

    public static void main(String[] args) {
        ConcurrentHashmapTest concurrentHashmapTest = new ConcurrentHashmapTest();
        new Thread(()->{
            synchronized (ConcurrentHashmapTest.class) {
                System.out.println("thread1 is waiting...");
                try {
                    //调用wait()方法，线程会放弃对象锁，进入等待此对象的等待锁定池
//                    concurrentHashmapTest.wait();
                    ConcurrentHashmapTest.class.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("thread1 is over!!!");
            }
        }).start();

        try {Thread.sleep(2000);} catch (Exception e) {e.printStackTrace();}

        new Thread(()->{
            synchronized (ConcurrentHashmapTest.class) {
                System.out.println("thread2 is sleep....");
                //只有针对此对象调用notify()方法后本线程才进入对象锁定池准备获取对象锁进入运行状态。
                ConcurrentHashmapTest.class.notify();//如果注释掉，则线程永远处于挂起状态。

                //sleep()方法导致了程序暂停执行指定的时间，让出cpu该其他线程，
                //但是他的监控状态依然保持者，当指定的时间到了又会自动恢复运行状态。
                //在调用sleep()方法的过程中，线程不会释放对象锁。
                try {Thread.sleep(2000);} catch (Exception e) {e.printStackTrace();}
                System.out.println("thread2 is over!!!");
            }
        }).start();
    }

}
