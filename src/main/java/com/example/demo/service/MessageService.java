package com.example.demo.service;

import com.example.demo.dao.entity.ChatMsg;
import com.example.demo.dao.repository.ChatMsgRepository;
import com.example.demo.dao.repository.NoticeMsgRepository;
import com.example.demo.dto.MsgDTO;
import com.example.demo.util.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
public class MessageService {
    @Autowired
    ChatMsgRepository chatMsgRepository;
    @Autowired
    NoticeMsgRepository noticeMsgRepository;

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

    /**
     * 获取当前用户未读信息条数
     */
    public ModelMap getUnreadNum(String user){
        return RestUtil.Success(noticeMsgRepository.countNoticeMsgByReceiverAndState(user, (byte) 1));
    }
}
