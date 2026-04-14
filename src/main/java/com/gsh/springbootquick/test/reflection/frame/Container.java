package com.gsh.springbootquick.test.reflection.frame;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * IOC实现原理
 */
public class Container {
    private Map<Class<?>, Method> methods;   // 储存的是 加@Bean注解的方法
    private Map<Class<?>, Object> services;  // 实现单例
    private Object config;

    public void init() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        methods = new HashMap<>();
        services = new HashMap<>();
        Class<?> clazz = Class.forName("com.gsh.springbootquick.test.reflection.frame.Config");
        Method[] declaredmethods = clazz.getDeclaredMethods();
        for (Method method : declaredmethods) {
            if (method.getAnnotation(Bean.class) != null) {
                this.methods.put(method.getReturnType(), method);  // 拿到所有生成对象的方法
            }
        }
        config = clazz.getConstructor().newInstance(); // 获得可以调用 map 集合方法生成实例的对象
    }

    /**
     *  通过类的class对象获取相应的服务实例
     */
    public Object getServiceInstanceByClass(Class<?> clazz) throws InvocationTargetException, IllegalAccessException {
        if (methods.containsKey(clazz)) {
            if (!services.containsKey(clazz)) {
                Method method = methods.get(clazz);
                Object instance = method.invoke(config);
                services.put(clazz, instance);
            }
            return services.get(clazz);
        }
        return null;
    }

    /**
     *  通过class对象创建普通实例，并实现将服务自动注入到对象中
     */
    public Object createInstance(Class<?> clazz) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor<?> constructor : constructors) {
            if (constructor.getAnnotation(Autowired.class) != null) {
                Class<?>[] parameterTypes = constructor.getParameterTypes();//所有服务实例
                Object[] arguments = new Object[parameterTypes.length];
                for (int i = 0; i < arguments.length; i++) {
                    arguments[i] = getServiceInstanceByClass(parameterTypes[i]);
                }
                return constructor.newInstance(arguments);//通过构造器自动将服务注入到实例中
            }
        }
        return clazz.getConstructor().newInstance();// 默认返回无参构造实例
    }
}
