package com.gsh.springbootquick.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author GSH
 * @create 2022/8/31 16:05
 */
public class ListRemoveTest {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);

        list.remove(10);
        System.out.println(list);
        //正常
//        for (int i = 0; i < list.size(); i++) {
//            if (i == 2) {
//                list.remove(i);
//            }
//        }
//        System.out.println(list);

        //java.util.ConcurrentModificationException
//        for (Integer i : list) {
//            list.remove(3);
//        }
//        System.out.println(list);

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
//            list.remove(integer);//这个方式移除，报异常java.util.ConcurrentModificationException
            iterator.remove(); //用这个方式没有问题
        }
        System.out.println(list);


    }
}
