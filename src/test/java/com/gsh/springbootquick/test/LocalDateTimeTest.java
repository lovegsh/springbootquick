package com.gsh.springbootquick.test;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * @author GSH
 * @create 2023/3/9 10:21
 * ZoneId: 时区ID，用来确定Instant和LocalDateTime互相转换的规则
 * Instant: 用来表示时间线上的一个点（瞬时）
 * LocalDate: 表示没有时区的日期, LocalDate是不可变并且线程安全的
 * LocalTime: 表示没有时区的时间, LocalTime是不可变并且线程安全的
 * LocalDateTime: 表示没有时区的日期时间, LocalDateTime是不可变并且线程安全的
 * Clock: 用于访问当前时刻、日期、时间，用到时区
 * Duration: 用秒和纳秒表示时间的数量（长短），用于计算两个日期的“时间”间隔
 * Period: 用于计算两个“日期”间隔
 */
public class LocalDateTimeTest {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    @Test
    public void test(){
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);

        LocalDate localDate2 = LocalDate.of(2018, 1, 13);
        LocalTime localTime2 = LocalTime.of(9, 43, 20);
        LocalDateTime localDateTime2 = LocalDateTime.of(2018, 1, 13, 9, 43, 20);
        System.out.println(localDate2);
        System.out.println(localTime2);
        System.out.println(localDateTime2);
    }

    @Test
    public void test2(){
        LocalDateTime localDateTime = LocalDateTime.now();
        //以下方法的参数都是long型，返回值都是LocalDateTime
        LocalDateTime plusYearsResult = localDateTime.plusYears(2L);
        LocalDateTime plusMonthsResult = localDateTime.plusMonths(3L);
        LocalDateTime plusDaysResult = localDateTime.plusDays(7L);
        LocalDateTime plusHoursResult = localDateTime.plusHours(2L);
        LocalDateTime plusMinutesResult = localDateTime.plusMinutes(10L);
        LocalDateTime plusSecondsResult = localDateTime.plusSeconds(10L);

        System.out.println("当前时间是 : " + localDateTime + "\n"
                + "当前时间加2年后为 : " + plusYearsResult + "\n"
                + "当前时间加3个月后为 : " + plusMonthsResult + "\n"
                + "当前时间加7日后为 : " + plusDaysResult + "\n"
                + "当前时间加2小时后为 : " + plusHoursResult + "\n"
                + "当前时间加10分钟后为 : " + plusMinutesResult + "\n"
                + "当前时间加10秒后为 : " + plusSecondsResult + "\n"
        );

        //也可以以另一种方式来相加减日期，即plus(long amountToAdd, TemporalUnit unit)
        //                  参数1 ： 相加的数量， 参数2 ： 相加的单位
        LocalDateTime nextMonth = localDateTime.plus(1, ChronoUnit.MONTHS);
        LocalDateTime nextYear = localDateTime.plus(1, ChronoUnit.YEARS);
        LocalDateTime nextWeek = localDateTime.plus(1, ChronoUnit.WEEKS);

        System.out.println("now : " + localDateTime + "\n"
                + "nextYear : " + nextYear + "\n"
                + "nextMonth : " + nextMonth + "\n"
                + "nextWeek :" + nextWeek + "\n"
        );
        //日期的减法用法一样，在此不再举例
    }

    @Test
    public void test3(){
        LocalDate localDate = LocalDate.now();
        //当前时间基础上，指定本年当中的第几天，取值范围为1-365,366
        LocalDate withDayOfYearResult = localDate.withDayOfYear(200);
        //当前时间基础上，指定本月当中的第几天，取值范围为1-29,30,31
        LocalDate withDayOfMonthResult = localDate.withDayOfMonth(31);
        //当前时间基础上，直接指定年份
        LocalDate withYearResult = localDate.withYear(2023);
        //当前时间基础上，直接指定月份
        LocalDate withMonthResult = localDate.withMonth(5);
        System.out.println("当前时间是 : " + localDate + "\n"
                + "指定本年当中的第200天 : " + withDayOfYearResult + "\n"
                + "指定本月当中的第31天 : " + withDayOfMonthResult + "\n"
                + "直接指定年份为2017 : " + withYearResult + "\n"
                + "直接指定月份为5月 : " + withMonthResult + "\n"
        );
    }

    @Test
    public void test4(){
        LocalDateTime localDateTime = LocalDateTime.now();
        int dayOfYear = localDateTime.getDayOfYear();
        int dayOfMonth = localDateTime.getDayOfMonth();
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        System.out.println("今天是" + localDateTime + "\n"
                + "本年当中第" + dayOfYear + "天" + "\n"
                + "本月当中第" + dayOfMonth + "天" + "\n"
                + "本周中星期" + dayOfWeek.getValue() + "-即" + dayOfWeek + "\n");

        //获取当天时间的年月日时分秒
        int year = localDateTime.getYear();
        Month month = localDateTime.getMonth();
        int day = localDateTime.getDayOfMonth();
        int hour = localDateTime.getHour();
        int minute = localDateTime.getMinute();
        int second = localDateTime.getSecond();
        System.out.println("今天是" + localDateTime + "\n"
                + "年 ： " + year + "\n"
                + "月 ： " + month.getValue() + "-即 "+ month + "\n"
                + "日 ： " + day + "\n"
                + "时 ： " + hour + "\n"
                + "分 ： " + minute + "\n"
                + "秒 ： " + second + "\n"
        );

    }

    @Test
    public void test5(){
        //判断两个时间点的前后
        LocalDate localDate1 = LocalDate.of(2017, 8, 8);
        LocalDate localDate2 = LocalDate.of(2018, 8, 8);
        boolean date1IsBeforeDate2 = localDate1.isBefore(localDate2);
        System.out.println("date1IsBeforeDate2 : " + date1IsBeforeDate2);
        // date1IsBeforeDate2 == true
        //heap
        LocalDate now = LocalDate.now();
        System.out.println("now : " + now + ", is leap year ? " + now.isLeapYear());
    }

    @Test
    public void test6(){

    }

    @Test
    public void test7(){

    }

    @Test
    public void test8(){

    }
}
