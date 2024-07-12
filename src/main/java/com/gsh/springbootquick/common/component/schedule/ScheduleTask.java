package com.gsh.springbootquick.common.component.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Date;

/**
 * @author GSH
 * @create 2023/3/22 20:03
 *
 */
//@EnableScheduling
//@Component
@Slf4j
public class ScheduleTask {
    @Scheduled(cron = "*/2 * * * * ?")
    public void task1() throws InterruptedException {
        log.error("我是task1111，我需要执行 5s 钟的时间，我的线程的 id == > {}，时间 == >{}", Thread.currentThread().getId(), LocalTime.now());
        Thread.sleep(5000);
        log.error("task1111 ending ,我的线程的 id == > {} , 时间 == > {}", Thread.currentThread().getId(), LocalTime.now());
    }

    @Scheduled(cron = "*/4 * * * * ?")
    public void task2() throws InterruptedException {
        log.error("我是task2222，我需要执行 2s 钟的时间，我的线程的 id == > {}，时间 == >{}", Thread.currentThread().getId(), LocalTime.now());
        Thread.sleep(2000);
        log.error("task2222 ending ,我的线程的 id == > {} , 时间 == > {}",Thread.currentThread().getId(), LocalTime.now());
    }
}
