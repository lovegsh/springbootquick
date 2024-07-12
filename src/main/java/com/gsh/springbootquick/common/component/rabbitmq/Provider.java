package com.gsh.springbootquick.common.component.rabbitmq;

import com.gsh.springbootquick.common.util.RabbitmqUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 *
 * MessageProperties.PERSISTENT_TEXT_PLAIN  消息持久化
 *
 * @author GSH
 * @create 2022/1/4 16:20
 */
@Component
public class Provider {
    public static final String QUEUE_NAME = "queue";
    public static final String EXCHANGE_NAME = "EXCHANGE";

    @Autowired
    private RabbitTemplate rabbitTemplate;
    public static void main(String[] args) {

    }







//    public static void main(String[] args) throws Exception {
//        queueDemo();
//        exchangeFanout();
//        exchangeDirect();
//    }

    public static void queueDemo() throws Exception{
        Channel channel = RabbitmqUtils.getChannel();
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        System.out.println("请输入内容：");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String message = sc.nextLine();
            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            System.out.println("消息发送完成:"+message);
        }
    }

    public static void exchangeFanout() throws Exception{
        Channel channel = RabbitmqUtils.getChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        System.out.println("请输入内容：");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String message = sc.nextLine();
            channel.basicPublish(EXCHANGE_NAME, "", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            System.out.println("消息发送完成:"+message);
        }
    }

    public static void exchangeDirect() throws Exception{
        Channel channel = RabbitmqUtils.getChannel();

//        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        System.out.println("请输入内容：");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String message = sc.nextLine();
            channel.basicPublish("amq.direct", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            System.out.println("消息发送完成:"+message);
        }
    }

    public static void exchangeTopic() throws Exception{
        Channel channel = RabbitmqUtils.getChannel();

//        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        System.out.println("请输入内容：");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String message = sc.nextLine();
            channel.basicPublish("amq.topic", "apple.aa.bb", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            System.out.println("消息发送完成:"+message);
        }
    }

}
