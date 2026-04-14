package com.gsh.springbootquick.test.reflection;

import lombok.val;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
//        val field = RefUser.publicStaticField;
//        System.out.println(field);
//        RefUser.myPublicStaticMethod();

//        Class<RefUser> userClass = RefUser.class;//不会触发静态代码块调用
//        Class<? extends RefUser> clazz2 = new RefUser("Bill", 22).getClass();
        Class<?> clazz = Class.forName("com.gsh.springbootquick.test.reflection.RefUser");//会触发静态代码块调用
        //===============字段======================
//        Field[] declaredFields = clazz.getDeclaredFields();//子类所有字段
//        for (Field declaredField : declaredFields) {
//            System.out.println(declaredField.getName());
//        }
//        Field[] fields = clazz.getFields();//子类和父类public字段
//        for (Field field : fields) {
//            System.out.println(field.getName());
//        }
//        Field[] personDeclaredFields = clazz.getSuperclass().getDeclaredFields();//获取父类所有字段

//        Field field = clazz.getDeclaredField("name");
//        System.out.println(field.getType());
//        System.out.println(field.getDeclaredAnnotation(MyAnnotation.class));// 注解需要标明 RetentionPolicy.SOURCE

//        Field comments = clazz.getDeclaredField("comments");
//        System.out.println(comments.getType());// 类型擦除的原始类型
//        System.out.println(comments.getGenericType());// 泛型

        // ========静态变量没有对象实例所以 null
//        System.out.println(clazz.getDeclaredField("publicStaticField").get(null));

//        Field pfield = clazz.getDeclaredField("privateStaticField");
//        pfield.setAccessible(true);// 私有变量开启访问权限
//        System.out.println(pfield.get(null));
//        pfield.set(null, 101);
//        System.out.println(pfield.get(null));

        //===============方法======================
//        for (Method declaredMethod : clazz.getDeclaredMethods()) {
//            System.out.println(declaredMethod);
//        }

//        Method method = clazz.getDeclaredMethod("myPublicStaticMethod");
//        method.invoke(null);

//        Method pmethod = clazz.getDeclaredMethod("myPrivateStaticMethod");
//        pmethod.setAccessible(true);
//        pmethod.invoke(null);

//        Method pmethod2 = clazz.getDeclaredMethod("myPrivateStaticMethod", String.class, String.class);
//        pmethod2.setAccessible(true);
//        pmethod2.invoke(null, "Hello", "World");

        //===============实例对象======================
//        Constructor<?> constructor = clazz.getDeclaredConstructor();//无参构造器
//        Object obj2 = constructor.newInstance();
//        if (obj2 instanceof RefUser) {
//            RefUser user = (RefUser) obj2;
//            System.out.println(clazz.getDeclaredField("name").get(user));
//        }
//        RefUser user = clazz.cast(obj2);// 报错！！动态生成无法在编译时确定类型
//        Class<RefUser> userClass2 = RefUser.class;
//        RefUser user = userClass2.cast(obj2);// xxx.class是确认的泛型不是？所以可以cast转换

        Constructor<?> constructor2 = clazz.getDeclaredConstructor(String.class, int.class);//无参构造器
        Object obj = constructor2.newInstance("Bill", 21);
//        RefUser bill = (RefUser) obj;
        System.out.println(clazz.getDeclaredField("name").get(obj));
        Field age = clazz.getDeclaredField("age");
        age.setAccessible(true);
        age.set(obj, 30);
        System.out.println(age.get(obj));

        Method publicMethod = clazz.getDeclaredMethod("myPrivateMethod");
        publicMethod.setAccessible(true);
        publicMethod.invoke(obj);

    }
}
