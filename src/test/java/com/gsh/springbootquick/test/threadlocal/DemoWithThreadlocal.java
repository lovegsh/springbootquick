package com.gsh.springbootquick.test.threadlocal;

public class DemoWithThreadlocal {
    ThreadLocal<String> tl = new ThreadLocal<>();

    private String content;

    public String getContent() {
//        return content;
        return tl.get();
    }

    public void setContent(String content) {
        tl.set(content);
    }

    public static void main(String[] args) {
        DemoWithThreadlocal demo = new DemoWithThreadlocal();

        for (int i = 0; i < 5; i++) {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.setContent(Thread.currentThread().getName() + "的数据");
                    System.out.println("----------------------");
                    System.out.println(Thread.currentThread().getName() + "---->" + demo.getContent());
                }
            });
            t1.setName("线程" + i);
            t1.start();
        }
    }
}
