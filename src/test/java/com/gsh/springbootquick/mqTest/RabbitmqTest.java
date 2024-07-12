package com.gsh.springbootquick.mqTest;

import org.apache.activemq.Message;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.postprocessor.MessagePostProcessorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author GSH
 * @create 2023/3/13 16:14
 */
@SpringBootTest
public class RabbitmqTest {
    @Autowired
    RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE_FANOUT = "fanout-exchange";
    private static final String EXCHANGE_TOPIC = "topic-exchange";
    private static final String DELAY_EXCHANGE_NAME = "delay-exchange";

    @Test
    public void test(){
        String message = "hello hahahahaha~~~~";
        rabbitTemplate.convertAndSend(EXCHANGE_FANOUT, "", message);
    }

    @Test
    public void testTopic(){
        String message = "hello topic~~~~";
        rabbitTemplate.convertAndSend(EXCHANGE_TOPIC, "product.add", message);
    }

    @Test
    public void testDelay(){
        String m = "hello topic~~~~";
        rabbitTemplate.convertAndSend(DELAY_EXCHANGE_NAME, "product.add", m, message -> {
            message.getMessageProperties().setExpiration("300000");
            return message;
        });
    }
}
