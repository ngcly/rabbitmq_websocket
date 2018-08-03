package com.example.demo.config;

import com.example.demo.dto.Greeting;
import com.example.demo.dto.HelloMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.util.HtmlUtils;


/**
 * websocket 事件监听类
 */
@Component
public class WebSocketEventListener {
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    /**
     * 连接成功事件
     * @param event
     */
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) headerAccessor.getHeader("simpUser");
        if(token.getName() != null) {
            HelloMessage message = new HelloMessage(token.getName()+"：上线了");
            messagingTemplate.convertAndSend("/topic/greeting",new Greeting(HtmlUtils.htmlEscape(message.getName())));
        }
    }

    /**
     * 断开连接事件
     * @param event
     */
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) headerAccessor.getHeader("simpUser");
        if(token.getName() != null) {
            HelloMessage message = new HelloMessage(token.getName()+"：下线了");
            messagingTemplate.convertAndSend("/topic/greeting",new Greeting(HtmlUtils.htmlEscape(message.getName())));
        }
    }
}