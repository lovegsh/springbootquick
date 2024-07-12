package com.gsh.springbootquick.test.JUC;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author GSH
 * @create 2021/12/11 19:58
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask futureTask = new FutureTask(new MyThread(), "111");

        Thread t1 = new Thread(futureTask);
        t1.start();
        System.out.println("******"+futureTask.get());

        FutureTask futureTask2 = new FutureTask(new MyThread2());
        Thread t2 = new Thread(futureTask2);
        t2.start();
        System.out.println("====="+futureTask2.get());

    }

}

class MyThread implements Runnable{

    @Override
    public void run() {

    }
}

class MyThread2 implements Callable{

    @Override
    public Object call() throws Exception {
        return 123;
    }
}