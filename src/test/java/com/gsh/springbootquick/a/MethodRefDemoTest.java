package com.gsh.springbootquick.a;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;


/**
 * Java 8 方法引用
 * Create By GSH on .
 */
class MethodRefDemoTest {

    @Test
    public void ConsumerTest(){
        Consumer<String> con = System.out::println;
        con.accept("123");

        this.con(s-> System.out.println("工资是："), 666);
    }

    public void con(Consumer<Double> con,double salary){
        con.accept(salary);
    }

    @Test
    public void applierTest(){
        Supplier<Long> supplier = System::currentTimeMillis;

        System.out.println(supplier.get());

    }

    @Test
    public void test4() {
        Function<Double, Long> fun1 = a -> (long) Math.round(a);
        Long apply = fun1.apply(14.7);
        System.out.println(apply);

        System.out.println();

        Function<Double, Long> fun2 = Math::round;
        Long apply2 = fun2.apply(14.3);
        System.out.println(apply2);

    }

    /**
     * 情况3   类：：示例方法
     * Comparator中的int compare(T t1, T t2)
     * String中的int t1.compareTo(t2)
     */
    @Test
    public void  test5() {
        Comparator<String> com1 = (s1, s2) -> s1.compareTo(s2);
        System.out.println(com1.compare("abc", "abd"));

        System.out.println();

        Comparator<String> com2 = String::compareTo;
        System.out.println(com2.compare("abc", "abf"));
    }

    /**
     * BiPredicate中的boolean test(T t1, T t2);
     * String中的boolean t1.equals(t2)
     */
    @Test
    public void test6() {
        BiPredicate<String, String> pre1 = (s1, s2) -> s1.equals(s2);
        System.out.println(pre1.test("abc", "abc"));

        System.out.println();

        BiPredicate<String, String> pre2 = String::equals;
        System.out.println(pre2.test("abc", "abd"));
    }

}