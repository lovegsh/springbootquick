package com.gsh.springbootquick.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @author GSH
 * @create 2022/6/22 14:17
 */
//@RestController
public class ActivemqController {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    @GetMapping("/queue/{str}")
    public String sendQueue(@PathVariable String str) {
        sendMessage(queue, str);
        return "success queue="+str;
    }

    @GetMapping("/topic/{str}")
    public String sendTopic(@PathVariable String str) {
        sendMessage(topic, str);
        return "success topic="+str;
    }

    // 发送消息，destination是发送到的队列，message是待发送的消息
    private void sendMessage(Destination destination, final String message){
        jmsMessagingTemplate.convertAndSend(destination, message);
    }
}
