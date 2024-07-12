package com.gsh.springbootquick.algorithm.a;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib动态代理适合没有接口的情况
 * @author GSH
 * @create 2021/12/24 10:35
 */
public class CglibDynamicProxyDemo {

    public static void main(String[] args) {

        CglibProxy cglibProxy = new CglibProxy();

        Man man = (Man)cglibProxy.newProxyInstance(new Man());

        man.sleep();
    }

}

class Man {

    public void sleep() {
        System.out.println("sleep~~~~");
    }
}


class CglibProxy implements MethodInterceptor {

    public Object newProxyInstance(Object clazz) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("---------------Cglib通用方法start----------------");

        Object invoke = methodProxy.invokeSuper(o, objects);

        System.out.println("---------------Cglib通用方法end----------------");
        return invoke;
    }
}