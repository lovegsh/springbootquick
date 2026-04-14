package com.gsh.springbootquick.test.executor;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class yjte {

    // 模拟的接口调用任务
    static class InterfaceCallTask implements Callable<String> {
        private final String taskId;

        public InterfaceCallTask(String taskId) {
            this.taskId = taskId;
        }

        @Override
        public String call() throws Exception {
            // 模拟接口调用耗时
            int i = new Random().nextInt(9000);
            Thread.sleep(i); // 随机0~5秒
//            Thread.sleep(5000); // 随机0~5秒
            return "Response from task: " + taskId;
        }
    }

    static Callable<String> task = () -> {
        try {
            Thread.sleep(5000);
            int i = new Random().nextInt(9000);
            Thread.sleep(i);
            return "success cost:" + i;
        } catch (Exception e) {
            throw e;
        }
    };

    public static void main(String[] args) {
        System.out.println("start···");
//        test1();
        test2();
    }

    public static void test1() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        String taskId = "task1";

        int maxRetries = 6;  // 最多调用6次
        boolean success = false;
        String result = null;

        for (int i = 0; i < maxRetries; i++) {
            Future<String> future = executor.submit(new InterfaceCallTask(taskId));
            try {
                result = future.get(3, TimeUnit.SECONDS); // 等待3秒
                success = true;
                break;
            } catch (TimeoutException e) {
                System.out.println("第 " + (i + 1) + " 次调用超时，3秒内未返回，将重试...");
            } catch (Exception e) {
                System.out.println("调用失败: " + e.getMessage());
            } finally {
                future.cancel(true); // 取消当前任务
            }
        }
        if (success) {
            System.out.println("接口调用成功，结果为: " + result);
        } else {
            System.out.println("接口调用失败，已重试6次，总共等待18秒");
        }
        executor.shutdown(); // 关闭线程池
    }

    public static void test2() {
        ExecutorService businessExecutor = Executors.newCachedThreadPool();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        int maxRetries = 6; // 最多重试6次
        AtomicInteger retryCount = new AtomicInteger(1);

        ScheduledFuture<?> scheduledFuture = executor.scheduleAtFixedRate(() -> {
            Future<String> future = businessExecutor.submit(task);
            try {
                String res = future.get(3, TimeUnit.SECONDS);
                System.out.println(res);
                businessExecutor.shutdownNow();
                executor.shutdownNow();
            } catch (TimeoutException e) {
                System.err.println("超时3秒，第" + retryCount);
                future.cancel(true);
            } catch (InterruptedException e) {
                System.err.println("3秒判断异常被打断："+e.getMessage());
            } catch (Exception e) {
                System.err.println("3秒判断异常："+e.getMessage());
            } finally {
                retryCount.getAndIncrement();
            }
        }, 0, 3, TimeUnit.SECONDS);

        new Thread(() -> {
            try {
                Thread.sleep(18000);
                if (!executor.isShutdown()) {
                    System.out.println("总耗时超过18秒，终止任务");
                    executor.shutdownNow();
                    businessExecutor.shutdownNow();
                }
            } catch (Exception e) {
                System.err.println("超时18秒异常："+e.getMessage());
            }
        }).start();
    }
}
