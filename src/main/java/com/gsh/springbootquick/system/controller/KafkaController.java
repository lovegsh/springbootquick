package com.gsh.springbootquick.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GSH
 * @create 2022/10/18 16:26
 */
@Slf4j
@RestController
public class KafkaController {

    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;

    @GetMapping("/send/{input}")
    public String sendFoo(@PathVariable String input) {
        System.out.println("kafka sending====");
        kafkaTemplate.send("mq", input);
        System.out.println("kafka sending finish====");
        return "success123";
    }

//    @KafkaListener(id = "webGroup", topics = "topic_input")
//    public void listen(String input) {
//        log.info("input value: {}" , input);
//    }
}
