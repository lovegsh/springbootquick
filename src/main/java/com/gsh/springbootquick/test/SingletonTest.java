package com.gsh.springbootquick.test;

public class SingletonTest {

    //懒汉
    private static class LazyHolder {
        private static final SingletonTest INSTANCE = new SingletonTest();
    }
    private SingletonTest (){
        System.out.println("SingletonTest constructor");
    }
    public static final SingletonTest getInstance() {
        return LazyHolder.INSTANCE;
    }

    public static void main(String[] args) {
//        SingletonTest instance = SingletonTest.getInstance();
//        SingletonTest instance2 = SingletonTest.getInstance();
//        System.out.println(instance == instance2);

//        SingletonTest instance3 = SingletonTest.getInstance2();
//        SingletonTest instance4 = SingletonTest.getInstance2();
//        System.out.println(instance3 == instance4);
    }

    //饿汉式单例类.在类初始化时，已经自行实例化
    private static final SingletonTest single = new SingletonTest();
    //静态工厂方法
    public static SingletonTest getInstance2() {
        return single;
    }
}
