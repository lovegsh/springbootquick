package com.gsh.springbootquick.test.reflection.frame;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException, NoSuchFieldException {
        Container container = new Container();
        container.init();

        String factoryName = "com.gsh.springbootquick.test.reflection.frame.Order";
        String serviceName = "customer";

        Class<?> clazz = Class.forName(factoryName);
        Object factory = container.createInstance(clazz);//创建order实例
        Field field = clazz.getDeclaredField(serviceName);
        field.setAccessible(true);
        Object service = field.get(factory);//通过order实例获取customer服务对象

        System.out.println(service);

        Method[] methods = service.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.getAnnotation(Printable.class) != null) {
                System.out.println(method.getName());
                method.invoke(service);
            }
        }

        System.out.println(service == container.getServiceInstanceByClass(Customer.class)); // true
    }
}
