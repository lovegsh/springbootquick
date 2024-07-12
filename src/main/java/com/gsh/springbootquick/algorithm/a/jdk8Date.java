package com.gsh.springbootquick.algorithm.a;

import java.time.*;

public class jdk8Date {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);

        LocalDateTime localDateTime2 = LocalDateTime.of(2021, 10, 23, 9, 58, 30);
        System.out.println(localDateTime2);

        System.out.println("月中日：" + localDateTime.getDayOfMonth());
        System.out.println("年中日：" + localDateTime.getDayOfYear());
        System.out.println("几月：" + localDateTime.getMonthValue());
        System.out.println("月：" + localDateTime.getMonth());
        System.out.println("分钟：" + localDateTime.getMinute());

        // 不改变原来的值
        System.out.println("月中日设置成22：" + localDate.withDayOfMonth(22));
        System.out.println("设置小时：" + localDateTime.withHour(8));

        Instant instant = Instant.now();
        System.out.println("获得本初子午线对应的标准时间：" + instant);

        OffsetDateTime atOffset = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println("添加时间的偏移量：" + atOffset);

        long epochMilli = instant.toEpochMilli();
        System.out.println("毫秒数：" + epochMilli);
        System.out.println("毫秒数创建：" + Instant.ofEpochMilli(epochMilli));
    }

}