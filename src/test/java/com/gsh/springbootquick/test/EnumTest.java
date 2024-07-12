package com.gsh.springbootquick.test;

import java.util.Arrays;

/**
 * @author GSH
 * @create 2022/11/23 14:31
 */
public class EnumTest {

    public static void main(String[] args) {
        System.out.println("valueOf()-->"+ResultEnum.valueOf("SUCCESS"));
        System.out.println(Enum.valueOf(ResultEnum.class, "ERROR"));
        System.out.println(ResultEnum.valueOf(ResultEnum.class, "错误"));
        System.out.println("values()-->"+ Arrays.toString(ResultEnum.values()));
        System.out.println("INFO and ERROAAAR");
    }
}

enum ResultEnum{

    SUCCESS(200, "success"),
    ERROR(400, "error"),
    错误(404, "404错误");

    int code;
    String name;

    ResultEnum(int code, String name){
        this.code = code;
        this.name = name;
    }
}
