package com.gsh.springbootquick.JUC;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author GSH
 * @create 2023/2/18 16:21
 */
public class ReentrantLockDemo {
    public static void main(String[] args) {
//        Phone phone = new Phone();
//        new Thread(() -> {
//            try {phone.sendSMS();} catch (Exception e) {e.printStackTrace();}
//        }, "Thread 1").start();
//        new Thread(() -> {
//            try {phone.sendSMS();} catch (Exception e) {e.printStackTrace();}
//        }, "Thread 2").start();

        Mobile mobile = new Mobile();
        new Thread(mobile).start();
        new Thread(mobile).start();
    }
}
class Phone{
    public synchronized void sendSMS()throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t -----invoked sendSMS()");
        Thread.sleep(3000);
        sendEmail();
    }

    public synchronized void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t +++++invoked sendEmail()");
    }
}


class Mobile implements Runnable{
    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }

    public void get() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t invoked get()");
            set();
        }finally {
            lock.unlock();
        }
    }
    public void set(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t invoked set()");
        }finally {
            lock.unlock();
        }
    }
}
