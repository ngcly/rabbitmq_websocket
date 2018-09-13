package com.example.demo.controller;

import com.example.demo.config.RabbitConfig;
import com.example.demo.dto.ChatMessage;
import com.example.demo.dto.MessageDTO;
import com.example.demo.dto.MsgDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.socket.config.WebSocketMessageBrokerStats;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
public class BackController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    WebSocketMessageBrokerStats webSocketMessageBrokerStats;
    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 登录页面
     *
     * @return login.html
     */
    @RequestMapping("/login")
    public String login(@RequestParam(required = false)String error, Model model) {
        model.addAttribute("error",error);
        return "login";
    }

    /**
     * 消息盒子页面
     */
    @RequestMapping("/msgbox")
    public String msgbox(){
        return "msgbox";
    }

    /**
     * 聊天记录页面
     */
    @RequestMapping("/chatlog")
    public String chatlog(){
        return "chatlog";
    }

    /**
     * 发现页面
     */
    @RequestMapping("/find")
    public String find(){
        return "find";
    }

    /**
     * 根据信息内容自动判断群发或者个人
     */
    @MessageMapping("/toTarget")
    public void sendToTarget(Principal principal, ModelMap modelMap){
        Map<String,String> map = new HashMap<>();
        if("chatMessage".equals(modelMap.get("emit"))){
            map.put("username",modelMap.get("sendName").toString());
            map.put("avatar",modelMap.get("sendAvatar").toString());
            map.put("type",modelMap.get("type").toString());
            map.put("content",modelMap.get("sendContent").toString());
            map.put("fromid",modelMap.get("sendId").toString());
        }
        MessageDTO message;
        MsgDTO msg;
       if("friend".equals(map.get("type"))){
           //发送人 此处为发送人Id
           map.put("id",modelMap.get("sendId").toString());
           message = MessageDTO.builder().emit(MessageDTO.MessageType.chatMessage).data(map).build();
           messagingTemplate.convertAndSendToUser(modelMap.get("receiveId").toString(),"/topic/greeting",message);
           msg = MsgDTO.builder()
                   .sender(map.get("username"))
                   .receiver(modelMap.get("receiveId").toString())
                   .content(map.get("content"))
                   .type("friend")
                   .build();
       }else{
           //发送群 此处Id 为 群ID
           map.put("id",modelMap.get("receiveId").toString());
           message = MessageDTO.builder().emit(MessageDTO.MessageType.chatMessage).data(map).build();
           messagingTemplate.convertAndSend("/topic/"+map.get("id"),message);
           msg = MsgDTO.builder()
                   .sender(map.get("username"))
                   .receiver(modelMap.get("receiveId").toString())
                   .content(map.get("content"))
                   .type("group")
                   .build();
       }
        rabbitTemplate.convertAndSend(RabbitConfig.CHAT_QUEUE, msg);
    }

}
