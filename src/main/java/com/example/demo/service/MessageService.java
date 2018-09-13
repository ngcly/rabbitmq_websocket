package com.example.demo.service;

import com.example.demo.dao.entity.ChatMsg;
import com.example.demo.dao.repository.ChatMsgRepository;
import com.example.demo.dto.MsgDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    ChatMsgRepository chatMsgRepository;

    /**
     * 保存聊天信息
     */
    public void saveChatMsg(MsgDTO msg){
        ChatMsg chatMsg = new ChatMsg();
        chatMsg.setSender(msg.getSender());
        chatMsg.setReceiver(msg.getReceiver());
        chatMsg.setContent(msg.getContent());
        chatMsg.setMsgType(msg.getType());
        chatMsgRepository.save(chatMsg);
    }
}
