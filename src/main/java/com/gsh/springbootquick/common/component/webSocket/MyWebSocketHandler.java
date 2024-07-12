package com.gsh.springbootquick.common.component.webSocket;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * @author GSH
 * @create 2023/3/19 22:36
 */
@Slf4j
@ServerEndpoint("/ws")
@Component
public class MyWebSocketHandler{

    @OnOpen
    public void afterConnectionEstablished(Session session) throws Exception {
        //  连接建立时调用该方法
        log.info(session.getId() + "连接建立" + session.getRequestURI());
        //  可以在此处记录下每个WebSocketSession的信息，方便后续操作
    }

    @OnMessage
    public void handleMessage(Session session, String message) throws Exception {
        //  当接收到客户端消息时调用该方法
        log.info(session.getRequestURI() + "收到消息：" + message);
//        String payload = message.getPayload().toString();
        //  处理消息
//        String replyMessage = "Server  received  message:  " + payload;

        //  发送回复消息
//        TextMessage replyTextMessage = new TextMessage(replyMessage);
        session.getBasicRemote().sendText("Server  received  message:  "+message);
    }

    @OnError
    public void handleTransportError(Session session, Throwable exception) throws Exception {
        //  出现异常时调用该方法
        log.error("WebSocketSession id: " + session.getId() + "出现异常", exception);
        //  可以在此处记录异常信息
        exception.printStackTrace();
    }

    @OnClose
    public void afterConnectionClosed(Session session) throws Exception {
        //  连接关闭时调用该方法
        log.info(session.getId() + "连接关闭" + session.getRequestURI());
        //  可以在此处清理资源
    }

//    public boolean supportsPartialMessages() {
//        //  是否支持部分消息
//        log.info(this.getClass().getName() + "支持部分消息");
//        return false;
//    }
}
