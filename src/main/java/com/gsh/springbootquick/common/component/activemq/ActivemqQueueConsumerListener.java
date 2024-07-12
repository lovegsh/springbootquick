package com.gsh.springbootquick.common.component.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author GSH
 * @create 2022/6/22 14:23
 */
//@Component
public class ActivemqQueueConsumerListener {

    @JmsListener(destination="${spring.activemq.queue-name}", containerFactory="queueListener")
    public void readActiveQueue(String message) {
        System.out.println("queue接收到：" + message);
    }

    @JmsListener(destination="${spring.activemq.queue-name}", containerFactory="queueListener")
    public void readActiveQueue2(String message) {
        System.out.println("queue2接收到：" + message);
    }
}
