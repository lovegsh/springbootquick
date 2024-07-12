package com.gsh.springbootquick.common.component.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author GSH
 * @create 2022/10/18 16:59
 */
//@Slf4j
//@Component
//public class KafkaConsumerListener {
//
//    @KafkaListener(topics = {"mq"}, id = "111")
//    public void listen(ConsumerRecord<Integer, String> integerStringConsumerRecord) {
//        System.out.println("kafka listening~~~~");
//        log.info("input value: {}" , integerStringConsumerRecord);
//    }
//}
