package com.gsh.springbootquick.algorithm.a;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author GSH
 * @create 2022/12/26 16:23
 */
public class ReflectionDemo {

    public static void main(String[] args) throws Exception {
        //1.加载Class对象
        Class clazz = Class.forName("com.gsh.demo.a.Student");

        //2.获取所有公有构造方法
        System.out.println("**********************所有公有构造方法*********************************");
        Constructor[] conArray = clazz.getConstructors();
        for(Constructor c : conArray){
            System.out.println(c);
        }

        System.out.println("************所有的构造方法(包括：私有、受保护、默认、公有)***************");
        conArray = clazz.getDeclaredConstructors();
        for(Constructor c : conArray){
            System.out.println(c);
        }

        System.out.println("*****************获取公有、无参的构造方法*******************************");
        Constructor con = clazz.getConstructor(null);
        //1>、因为是无参的构造方法所以类型是一个null,不写也可以：这里需要的是一个参数的类型，切记是类型
        //2>、返回的是描述这个无参构造函数的类对象。

        System.out.println("con = " + con);
        //调用构造方法
        Object obj = con.newInstance();
        //  System.out.println("obj = " + obj);
        //  Student stu = (Student)obj;

        System.out.println("******************获取私有构造方法，并调用*******************************");
        con = clazz.getDeclaredConstructor(char.class);
        System.out.println(con);
        //调用构造方法
        con.setAccessible(true);//暴力访问(忽略掉访问修饰符)
        obj = con.newInstance('男');

        // --------------------------------字段-----------------------------------------
        System.out.println();

        //2.获取字段
        System.out.println("************获取所有公有的字段********************");
        Field[] fieldArray = clazz.getFields();
        for(Field f : fieldArray){
            System.out.println(f);
        }
        System.out.println("************获取所有的字段(包括私有、受保护、默认的)********************");
        fieldArray = clazz.getDeclaredFields();
        for(Field f : fieldArray){
            System.out.println(f);
        }
        System.out.println("*************获取公有字段**并调用***********************************");
        Field f = clazz.getField("name");
        System.out.println(f);
        //产生Student对象--》Student stu = new Student();
        obj = clazz.getConstructor().newInstance();
        //为字段设置值
        f.set(obj, "刘德华");//为Student对象中的name属性赋值--》stu.name = "刘德华"
        //验证
        Student stu = (Student)obj;
        System.out.println("验证姓名：" + stu.name);

        System.out.println("**************获取私有字段****并调用********************************");
        f = clazz.getDeclaredField("phoneNum");
        System.out.println(f);
        f.setAccessible(true);//暴力反射，解除私有限定
        f.set(obj, "18888889999");
        System.out.println("验证电话：" + stu);

        System.out.println();
        // --------------------------------方法-----------------------------------------
        System.out.println("**********************所有公有方法*********************************");
        Method[] methods = clazz.getMethods();
        Arrays.stream(methods).forEach(System.out::println);

        System.out.println("**********************所有私有方法*********************************");
        Method[] declaredMethods = clazz.getDeclaredMethods();
        Arrays.stream(declaredMethods).forEach(System.out::println);

        System.out.println("**********************调用方法*********************************");
        Method eatingMethod = clazz.getDeclaredMethod("eating", String.class);
        Object eatingMethodReturn = eatingMethod.invoke(stu, "rice");
        System.out.println(eatingMethodReturn.toString());

        Method drinkingMethod = clazz.getDeclaredMethod("drinking", String.class);
        drinkingMethod.setAccessible(true);//AccessibleObject.checkAccess(AccessibleObject.java:674)
        Object drinkingMethodReturn = drinkingMethod.invoke(stu, "water");
        System.out.println(drinkingMethodReturn.toString());

        System.out.println("**********************main方法*********************************");
        Method methodMain = clazz.getMethod("main", String[].class);
        methodMain.invoke(null, (Object)new String[]{"a","b"});
        methodMain.invoke(stu, (Object)new String[]{"a", "b"});
        methodMain.invoke(null, new Object[]{new String[]{"a","b"}});
    }
}

class Student {
    public String name;
    protected int age;
    char sex;
    private String phoneNum;

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + ", sex=" + sex
                + ", phoneNum=" + phoneNum + "]";
    }
    //---------------构造方法-------------------
    //（默认的构造方法）
    Student(String str) {
        System.out.println("(默认)的构造方法 s = " + str);
    }

    //无参构造方法
    public Student() {
        System.out.println("调用了公有、无参构造方法执行了。。。");
    }

    //有一个参数的构造方法
    public Student(char name) {
        System.out.println("姓名：" + name);
    }

    //有多个参数的构造方法
    public Student(String name, int age) {
        System.out.println("姓名：" + name + "年龄：" + age);//这的执行效率有问题，以后解决。
    }

    //受保护的构造方法
    protected Student(boolean n) {
        System.out.println("受保护的构造方法 n = " + n);
    }

    //私有构造方法
    private Student(int age) {
        System.out.println("私有的构造方法   年龄：" + age);
    }
    //---------------方法-------------------
    public void eating(){
        System.out.println("eating...");
    }

    public String eating(String food){
        return "eating "+ food;
    }

    private String drinking(String food){
        return "drinking "+ food;
    }

    public static void main(String[] args) {
        System.out.println("this is main method....");
    }
}