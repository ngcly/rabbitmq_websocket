package com.example.demo.controller;

import com.example.demo.dto.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.WebSocketMessageBrokerStats;

import java.security.Principal;
import java.util.Date;
import java.util.Map;

@Controller
public class BackController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    WebSocketMessageBrokerStats webSocketMessageBrokerStats;

    /**
     * 广播所有用户
     */
    @MessageMapping("/hello")
    @SendTo("/topic/greeting")
    public ChatMessage greeting(Principal principal, ChatMessage chatMessage) throws Exception {
        ChatMessage message = new ChatMessage();
        message.setUsername(principal.getName());
        message.setContent(chatMessage.getContent());
        message.setType("CHAT");
        return message;
    }

    /**
     * 将消息定向到特定用户
     * 该注解还有broadcast属性，表明是否广播。就是当有同一个用户登录多个session时，是否都能收到。取值true/false
     * 当使用该注解时 接收端必须要加/user 前缀 也就是 /user/topic/greeting
     */
    @MessageMapping("/user")
    @SendToUser("/topic/greeting")
    public ChatMessage userGreeting(Principal principal, ChatMessage chatMessage) throws Exception {
        ChatMessage message = new ChatMessage();
        message.setUsername(principal.getName());
        message.setContent(chatMessage.getContent());
        message.setType("CHAT");
        return message;
    }

    /**
     * 使用restful风格
     */
    @MessageMapping("/user/{username}")
    @SendTo("/topic/greetings")
    public ChatMessage greeting(@DestinationVariable String username, ChatMessage chatMessage, @Headers Map<String, Object> headers) throws Exception {
        ChatMessage message = new ChatMessage();
        message.setUsername(username);
        message.setContent(chatMessage.getContent());
        message.setType("CHAT");
        return message;
    }

    /**
     * 自定义格式发送信息
     * 给某人发信息
     */
    @MessageMapping("/toUser")
    public void sendToUser(Principal principal, @Header("user")String user, ChatMessage chatMessage){
        //使用队列目的地 此处用topic不用queue 是因为一个session就会创建一个持久队列 有点浪费
        ChatMessage message = new ChatMessage();
        message.setUsername(principal.getName());
        message.setAvatar(chatMessage.getAvatar());
        message.setId(message.getId());
        message.setContent(chatMessage.getContent());
        message.setType("CHAT");
        message.setTimestamp(new Date().toString());
        messagingTemplate.convertAndSendToUser(user,"/topic/greeting",message);
    }

    /**
     * 群发信息
     */
    @MessageMapping("/toGroup")
    public void sendToAll(Principal principal,@Header("group")String group,ChatMessage chatMessage){
        //获取统计websocket的一些信息
        String stateInfo = webSocketMessageBrokerStats.getWebSocketSessionStatsInfo();
        ChatMessage message = new ChatMessage();
        message.setUsername(principal.getName());
        message.setContent(chatMessage.getContent());
        message.setType("CHAT");
        messagingTemplate.convertAndSend("/topic/"+group,message);
    }

}
