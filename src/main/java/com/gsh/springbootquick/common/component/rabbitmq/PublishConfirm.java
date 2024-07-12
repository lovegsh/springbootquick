package com.gsh.springbootquick.common.component.rabbitmq;

import com.gsh.springbootquick.common.util.RabbitmqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import com.rabbitmq.client.MessageProperties;

import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 单个确认：
 *  消息未持久化：347ms
 *  消息持久化：1361ms
 *
 * 异步批量确认：39ms
 *
 * @author GSH
 * @create 2022/1/5 13:13
 */
public class PublishConfirm {

    public static final String QUEUE_NAME = "PublishConfirm";

    public static void main(String[] args) throws Exception{

//        publishIndividually();

        publishAsync();

    }

    public static void publishIndividually() throws Exception{
        Channel channel = RabbitmqUtils.getChannel();

        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        channel.confirmSelect();//开启发布确认

        long begin = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {
            String message = i + "";
            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            boolean flag = channel.waitForConfirms();
            if (flag){
                System.out.println("发送完成:"+message);
            }
        }

        long finish = System.currentTimeMillis();

        System.out.println("单个确认耗时："+(finish-begin)+"ms");

    }

    public static void publishAsync() throws Exception{
        ConcurrentSkipListMap<Long, String> skipListMap = new ConcurrentSkipListMap<>();

        Channel channel = RabbitmqUtils.getChannel();

        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        channel.confirmSelect();//开启发布确认

        ConfirmCallback ackCallback = (deliveryTag, multiple) -> {
            if (multiple) {
                ConcurrentNavigableMap<Long, String> concurrentNavigableMap = skipListMap.headMap(deliveryTag);
                concurrentNavigableMap.clear();
            } else {
                skipListMap.remove(deliveryTag);
            }
        };

        ConfirmCallback nackCallback = (deliveryTag, multiple) -> {
            String message = skipListMap.get(deliveryTag);
            System.out.println("消息未确认:"+deliveryTag+"~~"+message);
        };

        channel.addConfirmListener(ackCallback, nackCallback);

        long begin = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {
            String message = i + "";
            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            skipListMap.put(channel.getNextPublishSeqNo(), message);
//            System.out.println("发送完成:"+message);
        }

        long finish = System.currentTimeMillis();

        System.out.println("异步确认，耗时："+(finish-begin)+"ms");

    }
}
