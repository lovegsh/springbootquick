package com.gsh.springbootquick.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GSH
 * @create 2023/3/12 15:44
 */
public class GenericTypeTest {

    public static void main(String[] args) {
        List<? super Manager> list = new ArrayList<>();
        //存
//        list.add(new Employee()); //编译错误
        list.add(new Manager());
        list.add(new CEO());

        List<? extends Manager> list2 = new ArrayList<>();
//        list2.add(new Employee()); //编译错误
//        list2.add(new Manager());    //会被强制转换为 Employee！
//        list2.add(new CEO());
    }
}

class CEO extends Manager {
}

class Manager extends Employee {
}

class Employee {
}