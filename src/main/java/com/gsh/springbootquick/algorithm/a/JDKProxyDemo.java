package com.gsh.springbootquick.algorithm.a;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理适合实现接口的情况
 *
 * 1.6和1.7  cglib速度快
 * 1.8优化了jdk使用字节码执行后，jdk速度快
 * Create By GSH on .
 */
public class JDKProxyDemo {

    public static void main(String[] args) {
        Superman superman = new Superman();
        Human proxyInstance = (Human) ProxyFactory.getProxyInstance(superman);
//        Object newProxyInstance = Proxy.newProxyInstance(Superman.class.getClassLoader(),
//                                                        superman.getClass().getInterfaces(),
//                                                        new MyInvocationHandler());
        String belief = proxyInstance.getBelief();
        System.out.println(belief);
        proxyInstance.eat("rice");
    }

}

interface Human{

    String getBelief();

    void eat(String food);
}

class Superman implements Human{

    @Override
    public String getBelief() {
        return "I believe I can fly!";
    }

    @Override
    public void eat(String food) {
        System.out.println("我喜欢吃" + food);
    }
}

class ProxyFactory {

    public static Object getProxyInstance(Object obj){
        MyInvocationHandler handler = new MyInvocationHandler();
        handler.setObj(obj);
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
    }
}

class MyInvocationHandler implements InvocationHandler {

    private Object obj;

    public void setObj(Object obj){
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("---------------JDK通用方法start----------------");

        Object invoke = method.invoke(obj, args);

        System.out.println("---------------JDK通用方法end----------------");
        return invoke;
    }
}