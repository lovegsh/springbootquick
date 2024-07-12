package com.gsh.springbootquick.common.component.rabbitmq;

import com.gsh.springbootquick.common.util.RabbitmqUtils;
import com.rabbitmq.client.*;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author GSH
 * @create 2022/1/4 16:36
 */
@Component
public class Consumer {
    public static final String QUEUE = "queue";
    public static final String EXCHANGE_NAME = "EXCHANGE";

    @RabbitListener(queues = RabbitmqConfig.QUEUE_FANOUT)
    public void process(Message message, Channel channel) throws IOException {
//        byte[] body = message.getBody();
        System.out.println("rabbitmq 接收到fanout消息: " + message);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);

    }

    @RabbitListener(queues = RabbitmqConfig.QUEUE_TOPIC)
    public void processTopic(Message message){
        byte[] body = message.getBody();
        System.out.println("rabbitmq 接收到topic消息: " + new String(body));
    }

//    @RabbitListener(queues = "delay-queue")
//    public void processDelay(Message message){
//        byte[] body = message.getBody();
//        System.out.println("rabbitmq 接收到delay消息: " + new String(body));
//    }

//    @RabbitListener(queues = "dead-queue")
//    public void processDead(Message message){
//        byte[] body = message.getBody();
//        System.out.println("rabbitmq 接收到dead消息: " + new String(body));
//    }





    public static void main(String[] args) throws Exception {
//        ack();
//        fanoutConsumer();
        directConsumer();
//        topicConsumer();
    }

    public static void ack() throws Exception {
        Channel channel = RabbitmqUtils.getChannel();
//        DeliverCallback deliverCallback = (consumerTag, message) -> {
//            System.out.println("接收到消息："+message.getBody());
//        };
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("接收到消息："+ new String(message.getBody()));
            //手动应答
            channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
            System.out.println("应答完成-"+message.getEnvelope().getDeliveryTag());
        };
        CancelCallback cancelCallback = consumerTag -> {
            System.out.println(consumerTag + " wrong");
        };

        channel.basicQos(1);
        channel.basicConsume(QUEUE, false, deliverCallback, cancelCallback);
    }

    public static void fanoutConsumer() throws Exception {
        Channel channel = RabbitmqUtils.getChannel();

        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue, EXCHANGE_NAME, "");

        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("接收到消息："+ new String(message.getBody()));
        };

        CancelCallback cancelCallback = consumerTag -> {
            System.out.println(consumerTag + " wrong");
        };

        channel.basicConsume(queue, true, deliverCallback, cancelCallback);
    }

    public static void directConsumer() throws Exception {
        Channel channel = RabbitmqUtils.getChannel();

        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, "amq.direct", QUEUE);

        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("接收到消息："+ new String(message.getBody()));
        };

        CancelCallback cancelCallback = consumerTag -> {
            System.out.println(consumerTag + " wrong");
        };

        channel.basicConsume(queueName, true, deliverCallback, cancelCallback);
    }

    /**
     * *匹配一个单词
     * #匹配0或多个单词
     * @throws Exception
     */
    public static void topicConsumer() throws Exception {
        Channel channel = RabbitmqUtils.getChannel();

        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue, "amq.topic", "*.aa.*");
        channel.queueBind(queue, "amq.topic", "lazy.#");

        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("接收到消息："+ new String(message.getBody()));
        };

        CancelCallback cancelCallback = consumerTag -> {
            System.out.println(consumerTag + " wrong");
        };

        channel.basicConsume(queue, true, deliverCallback, cancelCallback);
    }
}
