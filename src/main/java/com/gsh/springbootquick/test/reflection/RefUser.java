package com.gsh.springbootquick.test.reflection;

import com.gsh.springbootquick.system.bean.Person;

import java.util.List;

public class RefUser extends RefPerson {
    @MyAnnotation
    public String name;
    private final int age;
    private String email;
    private Message message;
    private List<String> comments;
    public static int publicStaticField = 1;
    private static int privateStaticField = 10;
    protected static int protectedStaticField = 20;
    protected int protectedField;

    static {
        System.out.println("static {} => UserClass is initialized");
    }

    public void myPublicMethod() {
        System.out.println("This is myPublic method");
    }

    private void myPrivateMethod() {
        System.out.println("This is myPrivate method");
    }

    private static void myPrivateStaticMethod(String content, String mark) {
        System.out.println("This is myPrivate Static method:"+content+", "+mark);
    }

    protected void myProtectedMethod() {
        System.out.println("This is myProtected method");
    }

    public static void myPublicStaticMethod() {
        System.out.println("This is myPublic static method");
    }

    public static void myPrivateStaticMethod() {
        System.out.println("This is myPrivate static method");
    }


    public RefUser(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public RefUser() {
        this.age = 18;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
