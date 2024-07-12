package com.gsh.springbootquick.common.component.schedule;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * @author GSH
 * @create 2023/3/22 21:14
 */
@Component
@DisallowConcurrentExecution
@Slf4j
public class Quartz1 extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.error("我是task1111，我将执行5s钟，线程名字 == > {} , 现在时间为 == > {}", Thread.currentThread().getId(), LocalTime.now());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.error("我是task1111，我已执行完成，线程名字 == > {} , 现在时间为 == > {}",Thread.currentThread().getId(), LocalTime.now());
    }
}
