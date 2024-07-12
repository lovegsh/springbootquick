package com.gsh.springbootquick.common.component.schedule;

import org.apache.logging.log4j.Level;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author GSH
 * @create 2023/3/22 21:12
 */
//@Configuration
public class QuartzConfig {
    protected static final Level OPERATING = Level.forName("BUS", 250);

    @Bean
    public JobDetail task1JobDetail() {
        return JobBuilder.newJob(Quartz1.class)
                .withIdentity("task1")
                .storeDurably(true)
                .build();
    }

    @Bean
    public JobDetail task2JobDetail() {
        return JobBuilder.newJob(Quartz2.class)
                .withIdentity("task2")
                .storeDurably(true)
                .build();
    }

    @Bean
    public CronTrigger task1Trigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("*/4 * * * * ?");
        return TriggerBuilder.newTrigger()
                .forJob(task1JobDetail())
                .withIdentity("task1")
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Bean
    public CronTrigger task2Trigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("*/2 * * * * ?");
        return TriggerBuilder.newTrigger()
                .forJob(task2JobDetail())
                .withIdentity("task2")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
