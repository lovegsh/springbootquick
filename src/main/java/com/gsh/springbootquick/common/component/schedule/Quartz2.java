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
public class Quartz2 extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.error("我是task2222，我将执行2s钟，线程名字 == > {} , 现在时间为 == > {}", Thread.currentThread().getId(), LocalTime.now());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.error("我是task2222，我已执行完成，线程名字 == > {} , 现在时间为 == > {}",Thread.currentThread().getId(), LocalTime.now());
    }
}
