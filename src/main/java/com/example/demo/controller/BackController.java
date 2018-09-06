package com.example.demo.controller;

import com.example.demo.dto.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.socket.config.WebSocketMessageBrokerStats;

import java.security.Principal;
import java.util.Map;

@Controller
public class BackController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    WebSocketMessageBrokerStats webSocketMessageBrokerStats;

    /**
     * 根据信息内容自动判断群发或者个人
     */
    @MessageMapping("/toTarget")
    public void sendToTarget(Principal principal, ModelMap modelMap){
        ChatMessage message = new ChatMessage();
        message.setUsername(modelMap.get("sendName").toString());
        message.setAvatar(modelMap.get("sendAvatar").toString());
        message.setType(modelMap.get("type").toString());
        message.setContent(modelMap.get("sendContent").toString());
        message.setFromid(modelMap.get("sendId").toString());
//        message.setTimestamp(String.valueOf(new Date().getTime()));
       if("friend".equals(message.getType())){
           //发送人 此处为发送人Id
           message.setId(modelMap.get("sendId").toString());
           messagingTemplate.convertAndSendToUser(modelMap.get("receiveId").toString(),"/topic/greeting",message);
       }else{
           //发送群 此处Id 为 群ID
           message.setId(modelMap.get("receiveId").toString());
           messagingTemplate.convertAndSend("/topic/"+message.getId(),message);
       }
    }

}
