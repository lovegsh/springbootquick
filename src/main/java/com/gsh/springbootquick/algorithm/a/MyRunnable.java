package com.gsh.springbootquick.algorithm.a;

public class MyRunnable implements Runnable {

    private int ticket = 100;

    @Override
    public void run() {
        while (ticket > 0) {
            System.out.println(Thread.currentThread() + ":" + ticket);
            ticket--;
        }
    }

    public static void main(String[] args) {

        MyThread mt1 = new MyThread();
        mt1.start();
        mt1.start();

        MyRunnable r = new MyRunnable();
        // Mrun r = new Mrun();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        Thread t3 = new Thread(r);
        t1.start();
        t2.start();
        t3.start();
        t1.start();

    }
}

class MyThread extends Thread {
    private int ticket = 100;

    @Override
    public void run() {
        while (ticket > 0) {
            System.out.println(Thread.currentThread() + ":" + ticket);
            ticket--;
        }
    }
}
