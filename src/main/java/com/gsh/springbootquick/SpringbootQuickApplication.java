package com.gsh.springbootquick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import javax.transaction.Transactional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

//@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication
@EnableJms
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringbootQuickApplication {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SpringApplication.run(SpringbootQuickApplication.class, args);
    }

    //Undertow Started SpringbootQuickApplication in 2.405 seconds (JVM running for 3.681)
    //Tomcat   Started SpringbootQuickApplication in 2.497 seconds (JVM running for 3.751)

    @Async
    public void asyncTask() {
        System.out.println("asyncTask()");
        long startTime = System.currentTimeMillis();
        try {
            //模拟耗时
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + "：void asyncTask()，耗时：" + (endTime - startTime));
    }

    @Async("asyncTaskExecutor")
    public Future<String> asyncTask(String s) {
        System.out.println("asyncTask(String s)");
        long startTime = System.currentTimeMillis();
        try {
            //模拟耗时
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + "：Future<String> asyncTask(String s)，耗时：" + (endTime - startTime));
        return AsyncResult.forValue(s);
    }

    @Async("asyncTaskExecutor")
    @Transactional
    public void asyncTaskForTransaction() {
        System.out.println("asyncTaskForTransaction()");
        //模拟异常
        throw new RuntimeException("模拟异常");
    }
}
