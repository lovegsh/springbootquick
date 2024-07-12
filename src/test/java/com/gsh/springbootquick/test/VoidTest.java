package com.gsh.springbootquick.test;

import java.lang.reflect.Method;

/**
 * @author GSH
 * @create 2023/4/11 17:07
 */
public class VoidTest {

    public static void main(String[] args) throws Throwable {
        for (Method method : VoidTest.class.getMethods()) {
            System.out.println(method.getName() + "  " + method.getReturnType());
//            if (method.getReturnType().equals(Void.TYPE)) {
//                System.out.println("返回void的方法是：" + method.getName());
//            } else if (method.getReturnType().equals(Integer.TYPE)) {
//                System.out.println("返回int的方法是：" + method.getName());
//            }
        }
    }

    public void a() {
    }

    public int b() {
        return 1;
    }
}
