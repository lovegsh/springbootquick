package com.gsh.springbootquick.test.threadlocal;

public class DemoNoThreadlocal {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static void main(String[] args) {
        DemoNoThreadlocal demo = new DemoNoThreadlocal();

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
