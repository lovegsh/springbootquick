package com.gsh.springbootquick.common.component.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author GSH
 * @create 2022/6/22 14:24
 */
//@Component
public class ActivemqTopicConsumerListener {

    @JmsListener(destination="${spring.activemq.topic-name}", containerFactory="topicListener")
    public void readActiveQueue(String message) {
        System.out.println("topic接受到：" + message);
    }

    @JmsListener(destination="${spring.activemq.topic-name}", containerFactory="topicListener")
    public void readActiveQueue2(String message) {
        System.out.println("topic2接受到：" + message);
    }
}
