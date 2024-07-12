package com.gsh.springbootquick.common.component.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author GSH
 * @create 2023/3/13 15:56
 */
@Configuration
public class RabbitmqConfig {
    public static final String EXCHANGE_FANOUT = "fanout-exchange";
    public static final String QUEUE_FANOUT = "fanout-queue";
    public static final String EXCHANGE_TOPIC = "topic-exchange";
    public static final String QUEUE_TOPIC = "topic-queue";
    public static final String EXCHANGE_DELAY = "delay-exchange";
    public static final String QUEUE_DELAY = "delay-queue";
    public static final String DELAY_QUEUE_ROUTING_KEY = "delay.#";


    /**
     * 延迟队列配置
     * <p>
     * 1、params.put("x-message-ttl", 5 * 1000);
     * 第一种方式是直接设置 Queue 延迟时间 但如果直接给队列设置过期时间,这种做法不是很灵活,（当然二者是兼容的,默认是时间小的优先）
     * 2、rabbitTemplate.convertAndSend(book, message -> {
     * message.getMessageProperties().setExpiration(2 * 1000 + "");
     * return message;
     * });
     * 第二种就是每次发送消息动态设置延迟时间,这样我们可以灵活控制
     **/
    @Bean
    public Queue delayOrderQueue() {
        Map<String, Object> params = new HashMap<>();
        // x-dead-letter-exchange 声明了队列里的死信转发到的DLX名称，
        params.put("x-dead-letter-exchange", EXCHANGE_DELAY);
        // x-dead-letter-routing-key 声明了这些死信在转发时携带的 routing-key 名称。
        params.put("x-dead-letter-routing-key", DELAY_QUEUE_ROUTING_KEY);
        return new Queue(QUEUE_DELAY, true, false, false, params);
    }

    /**
     * topic 广播交换机
     * @return
     */
    @Bean
    public TopicExchange topicExchange(){
         return new TopicExchange(EXCHANGE_TOPIC, true, false);
    }

    @Bean
    public Queue topicQueue(){
        return new Queue(QUEUE_TOPIC, true, false, false);
    }

    @Bean
    public Binding bindingExchangeTopic(){
        return BindingBuilder.bind(queue()).to(topicExchange()).with("product.*");
    }

    /**
     * fanout  交换机
     * @return
     */
    @Bean
    public FanoutExchange exchange(){
        return new FanoutExchange(EXCHANGE_FANOUT, true, false);
    }

    @Bean
    public Queue queue(){
        return new Queue(QUEUE_FANOUT, true,  false, true);
    }

    @Bean
    public Binding queueBing(){
        return BindingBuilder.bind(queue()).to(exchange());
    }

}
