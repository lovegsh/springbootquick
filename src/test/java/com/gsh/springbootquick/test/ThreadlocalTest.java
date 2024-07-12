package com.gsh.springbootquick.test;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author GSH
 * @create 2022/8/30 11:19
 */
public class ThreadlocalTest {

    static ThreadLocal t1 = new ThreadLocal();
    static ThreadLocal t2 = new ThreadLocal();
    static InheritableThreadLocal it1 = new InheritableThreadLocal();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        t1.set("a");
//        t2.set("b");
//        System.out.println("t1 = " + t1.get());
//        System.out.println("t2 = " + t2.get());
//        Map<ThreadLocal, Object> threadLocalMap = getThreadLocalMap();
//        threadLocalMap.forEach((k,v)-> System.out.println(k+"======"+v));
//        System.out.println(threadLocalMap);



        it1.set("InheritableThreadLocal");
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Future<Object> future = pool.submit(() -> {
            System.out.println("pool");
            return it1.get();
        });
        System.out.println(future.get());
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
//        it1.set("InheritableThreadLocal==>2");
//        Future<Object> future2 = pool.submit(() -> {
//            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
//            System.out.println("pool2");
//            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
//            System.out.println("pool3");
//            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
//            System.out.println("pool4");
//            return it1.get();
//        });
//        System.out.println(future2.get());

        pool.execute(() -> {
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("pool2");
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("pool3");
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("pool4");
        });
        System.out.println("execute complete");

        pool.shutdown();  //等任务执行完毕再停止
        System.out.println("isShutdown = "+pool.isShutdown());
//        pool.shutdownNow();   //直接中断,sleep抛出异常打断interruptException
        System.out.println("isTerminated = "+pool.isTerminated());

        FutureTask task = new FutureTask<>(() -> "123");
        Future<?> submit = pool.submit(task);
        Future<String> future1 = pool.submit(() -> "444");
        System.out.println(submit.get());
        System.out.println(future1.get());

        FutureTask task2 = new FutureTask<>(() -> {
            System.out.println("FutureTask");
            return "123";
        });
        Thread thread = new Thread(task2);
        thread.start();
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("cancel = "+task2.cancel(true));
        System.out.println("isDone = "+task2.isDone());
    }

    public static Map<ThreadLocal, Object> getThreadLocalMap() {
        Map<ThreadLocal, Object> threadLocals = new HashMap<>();
        Thread thread = Thread.currentThread();
        try {
            Field threadLocalsField = Thread.class.getDeclaredField("threadLocals");
            threadLocalsField.setAccessible(true);
            Object threadLocalMap = threadLocalsField.get(thread);
            Field tableField = threadLocalMap.getClass().getDeclaredField("table");
            tableField.setAccessible(true);
            Object[] table = (Object[]) tableField.get(threadLocalMap);
            for (int i = 0; i < table.length; i++) {
                Object entry = table[i];
                if (entry != null) {
                    WeakReference<ThreadLocal> threadLocalRef = (WeakReference<ThreadLocal>) entry;
                    ThreadLocal threadLocal = threadLocalRef.get();
                    if (threadLocal != null) {
                        Object threadLocalValue = threadLocal.get();
                        threadLocals.put(threadLocal, threadLocalValue);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return threadLocals;
    }
}
