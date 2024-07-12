package com.gsh.springbootquick.mqTest;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @author GSH
 * @create 2022/10/18 16:28
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = KafkaTest.class)
//@EmbeddedKafka(count = 4,ports = {9092,9093,9094,9095})//默认只写注解不加参数的情况下，是创建一个随机端口的Broker，在启动的日志中会输出具体的端口以及默认的一些配置项。
//public class KafkaTest {
//    @Test
//    public void contextLoads()throws IOException {
//        System.in.read();
//    }
//}
