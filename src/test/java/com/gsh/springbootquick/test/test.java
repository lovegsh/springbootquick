package com.gsh.springbootquick.test;

import org.junit.jupiter.api.Test;

//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
import java.util.*;
//import java.util.stream;

/**
 * @author GSH
 * @create 2022/8/25 11:09
 */
public class test {

    public static void main(String[] args) {
//        InputStreamReader reader = new InputStreamReader(System.in);
//        char[] chars = new char[5];
//        int len;
//        StringBuilder str = new StringBuilder();
//        try(reader) {
//            while((len = reader.read(chars)) != -1){
//                String s = new String(chars, 0, len);
//                System.out.println(s);
//                str = str.append(s);
//
//            }
//            System.out.println("str = "+str);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        Consumer<String> con = (@Deprecated var t) -> System.out.println(t.toUpperCase());
//        Stream.of("a","abc").forEach(con);

//        try {
//            HttpClient client = HttpClient.newHttpClient();
//            HttpRequest request = HttpRequest.newBuilder(URI.create("http://127.0.0.1:8080/test/")).build();
//            HttpResponse.BodyHandler<String> responseBodyHandler = HttpResponse.BodyHandlers.ofString();
//            HttpResponse<String> response = client.send(request, responseBodyHandler);
//            String body = response.body();
//            System.out.println(body);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void test() {
        int[] arr = new int[]{5, 2, 8, 6, 4, 1, 9};
        System.out.println("arr = " + arr);
        System.out.println("Arrays.stream(arr) = " + Arrays.stream(arr));
        System.out.println("Arrays.stream(arr).max() = " + Arrays.stream(arr).max());
        System.out.println("Arrays.stream(arr).max().getAsInt() = " + Arrays.stream(arr).max().getAsInt());
        demo demo = new demo();
        demo.method();
        demo.method2();

        HashSet<Object> set = new HashSet<>();
        set.add(null);
        set.add(null);
        System.out.println("set = "+set);
        HashMap<String, Object> map = new HashMap<>();
        map.put(null,null);
        System.out.println("map = "+map);
        map.put(null,"a");
        map.put(null,"b");
        System.out.println("map = "+map);

//        Hashtable<String, Object> hashtable = new Hashtable<>();
//        hashtable.put(null, "c");
//        System.out.println("hashtable = "+hashtable);

        //        List.of(arr);

//        BigInteger
    }
}

@FunctionalInterface
interface funcInterface {

    void method();

    default void method2() {
        System.out.println(this.getClass().getName());
    }

    static void method3() {

    }

    boolean equals(Object o);
}

@FunctionalInterface
interface funcInterface2 extends funcInterface {

    void m2();

    @Override
    default void method() {
        System.out.println(this.getClass().getName());
    }
}

class demo implements funcInterface2 {


    @Override
    public void method2() {
        funcInterface2.super.method2();
        System.out.println("class demo method2()");
    }

    @Override
    public void m2() {

    }

    @Override
    public void method() {
        funcInterface2.super.method();
        System.out.println("class demo method()");
    }



}


